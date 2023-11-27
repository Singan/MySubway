package com.traffic.member.dto.login_dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class SNSLoginDto {

    private final String id;

    private final String secret;

    private final String redirectUri;

    private final String authorization;

    private final String scope;

    private final String token ;

    private final String profile;

}
