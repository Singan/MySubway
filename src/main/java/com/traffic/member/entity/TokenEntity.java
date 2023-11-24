package com.traffic.member.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TokenEntity {

    private String access_token;

    private String refresh_token;

    private String token_type;

    private Integer expires_in;

    private String scope;

}
