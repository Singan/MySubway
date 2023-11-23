package com.traffic.member.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString
public class SignupResEntity {

    @Schema(example = "test@test.com", description = "이메일", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(example = "테스트", description = "닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @Schema(example = "01012341234", description = "핸드폰번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String mobileNo;
}
