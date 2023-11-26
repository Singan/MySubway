package com.traffic.member.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;


import javax.validation.constraints.NotEmpty;


@Data
@ToString
public class SignupReqDto {

    @Schema(example = "id", description = "회원 고유 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String id;

    @Schema(example = "test@test.com", description = "이메일", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String email;

    @Schema(example = "test1010!@", description = "비밀번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String password;

    @Schema(example = "홍길동", description = "이름", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(example = "01012341234", description = "핸드폰번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String mobileNo;

    @Schema(example = "100", description = "회원가입 유형 - 100: 카카오 로그인, 200: 네이버 로그인", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String memberType;


}
