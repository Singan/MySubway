package com.traffic.member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.traffic.common.entity.ResEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SignupResDto extends SigninResDto {

    @Schema(example = "id", description = "회원 고유 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String id;

    @Schema(example = "test@test.com", description = "이메일", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(example = "테스트", description = "닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @Schema(example = "01012341234", description = "핸드폰번호 (본인인증 및 SNS 리턴값)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String mobileNo;
}
