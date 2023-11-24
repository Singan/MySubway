package com.traffic.member.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "naver.client")

@ConstructorBinding
public class NaverUtil {
    private final String id;
    private final String secret;
    private final String redirectUri;

}
