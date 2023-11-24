package com.traffic.member.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode()
@Data
@ToString
public class SigninReqDto {

    @Schema(example = "100", description = "로그인유형 - 100: 카카오 로그인, 200: 네이버 로그인", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String signinType;

    @Schema(example = "101293813", description = "SNS 회원 식별값", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String snsIdentifier;

    @Schema(example = "test@test.com", description = "ID (이메일)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;

    @Schema(example = "test", description = "비밀번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String password;

}
