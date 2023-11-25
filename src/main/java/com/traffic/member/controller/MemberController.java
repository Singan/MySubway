package com.traffic.member.controller;

import com.traffic.member.api.NaverUtil;
import com.traffic.member.api.kakao.KakaoApiClient;
import com.traffic.member.dto.req.SigninReqDto;
import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.dto.res.SignupResDto;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/member")
@Tag(name = "MemberController", description = "회원 서비스")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 로그인", security = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "basicAuth")})
    @Parameters({
            @Parameter(name = "signIn", example = "id, pw", description = "내용 설명", required = true)
    })
    @ResponseBody
    @GetMapping(value = "signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public void signIn()  {
    }
    @GetMapping("/naver")
    public String naverLoginUrl(HttpServletResponse response) throws Exception{
        return memberService.getNaverAuthorizeUrl();
    }
    @GetMapping("/oauth")
    public void naverAccessToken(@RequestParam String code) throws IOException {
        memberService.getAccessToken(code);
    }

    @Operation(summary = "카카오 로그인")
    @PostMapping("/kakao")
    public ResponseEntity<TokenEntity> loginKakao(@RequestBody SignupReqDto reqDto) throws Exception {
        return ResponseEntity.ok(memberService.login(reqDto));
    }

    @Operation(summary = "카카오 토큰", security = {@SecurityRequirement(name = "basicAuth")})
    @ResponseBody
    @GetMapping(value = "/oauth/kakao", produces = MediaType.APPLICATION_JSON_VALUE)
    public void kakaoToken(@RequestBody SigninReqDto reqEntity, @RequestParam String code) throws Exception {
         memberService.kakaoLogin(reqEntity, code);
    }



}
