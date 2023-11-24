package com.traffic.member.controller;

import com.traffic.member.api.NaverUtil;
import com.traffic.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@RequestMapping("/1.0/member")
@Tag(name = "MemberController", description = "회원 서비스")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public void signUp(){
    }

    @GetMapping
    public void signIn() throws Exception{
        System.out.println(memberService.getNaverAuthorizeUrl());
    }

}
