package com.traffic.member.dto.login_dto;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "oauth.naver.client")
@ConstructorBinding
public class NaverSNSLoginDto extends SNSLoginDto {
    public NaverSNSLoginDto(String id, String secret, String redirectUri, String authorization, String token) {
        super(id, secret, redirectUri, authorization, token);
    }
}
