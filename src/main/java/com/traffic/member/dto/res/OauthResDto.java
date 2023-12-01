package com.traffic.member.dto.res;

import com.google.gson.Gson;
import com.traffic.common.entity.ResEntity;
import lombok.*;

@Getter
@ToString
@Setter
public class OauthResDto extends ResEntity {

    private Long id;

    private String email;

    private String nickname;

    private String type;

    private KakaoAccount kakao_account;

    private Properties properties;

    private NaverProfileDto response;


    @Getter
    @ToString
    public static class KakaoAccount {
        private String email;
    }

    @Getter
    @ToString
    public static class Properties {
        private String nickname;
    }


}
