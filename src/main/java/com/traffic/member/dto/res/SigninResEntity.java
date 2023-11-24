package com.traffic.member.dto.res;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode()
@Data
@ToString
public class SigninResEntity {

    @Schema(example = "6003caf6-187d-4000-ac47-0e333f38f6c8", description = "access_token")
    private String access_token;

    @Schema(example = "bearer", description = "token_type")
    private String token_type;

    @Schema(example = "7537aa29-fe8c-43fe-9e4a-ddff315bb942", description = "refresh_token")
    private String refresh_token;

    @Schema(example = "86399", description = "expires_in")
    private Integer expires_in;

    @Schema(example = "user", description = "scope")
    private String scope;
}
