package com.traffic.member.controller;

import com.traffic.member.api.NaverUtil;
import com.traffic.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/1.0/member")
@Tag(name = "MemberController", description = "회원 서비스")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @PostMapping
    public void signUp(){
    }

    @Operation(summary = "회원 로그인", security = {@SecurityRequirement(name = "bearerAuth"), @SecurityRequirement(name = "basicAuth")})
    @Parameters({
            @Parameter(name = "signIn", example = "id, pw", description = "내용 설명", required = true)
    })
    @ResponseBody
    @GetMapping(value = "signIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public void signIn() throws Exception{
        System.out.println(memberService.getNaverAuthorizeUrl());
    }

}
