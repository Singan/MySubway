package com.traffic.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Long expiresIn;

    public static TokenEntity of(String accessToken, String refreshToken, String tokenType, Long expiresIn) {
        return new TokenEntity(accessToken, refreshToken, tokenType, expiresIn);
    }

}
