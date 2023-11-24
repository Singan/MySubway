package com.traffic.member.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;


import javax.validation.constraints.NotEmpty;


@Data
@ToString
public class SignupReqDto {

    // @Schema << swagger에서 보여주는거 지우면 안됨 + 예시로 적어둔거 바뀔수 있음
    @Schema(example = "test@test.com", description = "이메일", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String email;

    @Schema(example = "test1010!@", description = "비밀번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String password;

    @Schema(example = "홍길동", description = "이름", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(example = "01012341234", description = "핸드폰번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String mobileNo;



}
