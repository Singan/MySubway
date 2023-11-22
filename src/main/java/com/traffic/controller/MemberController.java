package com.traffic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/member")
@RequiredArgsConstructor
public class MemberController {
    @PostMapping
    public void signUp(){
    }

    @GetMapping
    public void signIn(){
    }

}
