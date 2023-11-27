package com.traffic.member.dto.res;

import com.traffic.common.entity.ResEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class OauthResDto extends ResEntity {

    private String id;
    private NaverProfileDto response;
}
