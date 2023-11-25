package com.traffic.member.dto.login_dto;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "oauth.kakao.client")
@ConstructorBinding
public class KakaoSNSLoginDto extends SNSLoginDto {

    public KakaoSNSLoginDto(String id, String secret, String redirectUri, String authorization, String token) {
        super(id, secret, redirectUri, authorization, token);
    }
}
