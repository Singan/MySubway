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
    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;
    public static TokenEntity of(String access_token, String refresh_token, String token_type, Long expires_in) {
        return new TokenEntity(access_token, refresh_token, token_type, expires_in);
    }

}
