package com.traffic.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)

public class TokenEntity {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
    public static TokenEntity of(String accessToken, String refreshToken, String tokenType, Long expiresIn) {
        return new TokenEntity(accessToken, refreshToken, tokenType, expiresIn);
    }

}
