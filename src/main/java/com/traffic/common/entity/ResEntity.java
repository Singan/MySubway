package com.traffic.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResEntity {

    @Schema(example = "100", description = "결과코드 100: SUCCESS, 그외 실패")
    private String code = "100";

    @Schema(example = "성공", description = "결과 메세지")
    private String message = "성공";

    @Schema(description = "결과 상세 메세지")
    private String subMessage = "";

}
