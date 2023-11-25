package com.traffic.member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.traffic.common.entity.ResEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SignupResDto extends ResEntity {

    @Schema(example = "6003caf6-187d-4000-ac47-0e333f38f6c8", description = "access_token")
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(example = "bearer", description = "token_type")
    @JsonProperty("token_type")
    private String tokenType;

    @Schema(example = "7537aa29-fe8c-43fe-9e4a-ddff315bb942", description = "refresh_token")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @Schema(example = "86399", description = "expires_in")
    @JsonProperty("expires_in")
    private String expiresIn;

    @Schema(example = "86399", description = "refresh_token_expires_in")
    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;

    @Schema(example = "user", description = "scope")
    @JsonProperty("scope")
    private String scope;
}
