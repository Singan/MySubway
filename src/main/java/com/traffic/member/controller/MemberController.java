package com.traffic.member.controller;

import com.traffic.member.dto.login_dto.SNSLoginDto;
import com.traffic.member.dto.res.OauthResDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.service.AuthLoginService;
import com.traffic.member.dto.login_dto.KakaoSNSLoginDto;
import com.traffic.member.dto.login_dto.NaverSNSLoginDto;
import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@Tag(name = "MemberController", description = "회원 서비스")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthLoginService authLoginService;
    private final NaverSNSLoginDto naverSNSLoginDto;
    private final KakaoSNSLoginDto kakaoSNSLoginDto;

    @Operation(summary = "회원 로그인", security = {@SecurityRequirement(name = "bearerAuth")})
    @Parameters({
            @Parameter(name = "signIn", example = "id, pw", description = "내용 설명", required = true)
    })
    @ResponseBody
    @PostMapping(value = "sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<TokenEntity> signIn(@RequestBody SignupReqDto reqDto)  {
//        TokenEntity token = memberService.login(reqDto);
//        return ResponseEntity.ok(token);
        return null;
    }

    @Operation(summary = "회원 로그인", security = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "basicAuth")})
    @Parameters({
            @Parameter(name = "signUp", example = "id, pw", description = "회원 가입", required = true)
    })
    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupReqDto reqDto) {
        String userEmail = memberService.newMember(reqDto);
        return ResponseEntity.ok("사용자 이메일: " + userEmail);
    }

    @Operation(summary = "로그인 타입에 따라 SNS 등 분기처리")
    @GetMapping("/sign_in/{type}")
    public ResponseEntity<String> loginKakao(@PathVariable(name = "type") String type) throws Exception {
        ResponseEntity responseEntity = null;
        System.out.println("Sign 실행");
        switch (type){
            case "naver":
                responseEntity = ResponseEntity.ok(authLoginService.getAuthorization(naverSNSLoginDto));
                break;
            case "kakao":
                responseEntity = ResponseEntity.ok(authLoginService.getAuthorization(kakaoSNSLoginDto));
                break;
            default:
        }
        return responseEntity;
    }

    @Operation(summary = "SNS 로그인 토큰", security = {@SecurityRequirement(name = "basicAuth")})
    @ResponseBody
    @GetMapping(value = "/oauth/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenEntity> kakaoToken(@PathVariable(name = "type") String type, @RequestParam String code) throws Exception {
        SigninResDto oauthToken = null;
        OauthResDto oauthResDto = null;
        switch (type){
            case "naver":
                oauthToken = authLoginService.getAccessToken(naverSNSLoginDto, code);
                oauthResDto = authLoginService.getProfile(naverSNSLoginDto, oauthToken.getAccess_token());
                break;
            case "kakao":
                oauthToken= authLoginService.getAccessToken(kakaoSNSLoginDto, code);
                oauthResDto = authLoginService.getProfile(kakaoSNSLoginDto, oauthToken.getAccess_token());
                break;
            default:
                break;
        }
        if (!memberService.memberExists(oauthResDto.getId())) {
            TokenEntity naverToken = memberService.newMemberAndLogin(oauthResDto);
            return ResponseEntity.ok(naverToken);
        } else {
            TokenEntity naverToken = memberService.login(oauthResDto);
            return ResponseEntity.ok(naverToken);
        }
//        return ResponseEntity.badRequest().build();
    }
}
