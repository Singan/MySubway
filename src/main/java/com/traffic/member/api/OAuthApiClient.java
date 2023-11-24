package com.traffic.member.api;

import com.traffic.common.enums.OAuthProvider;
import com.traffic.member.dto.req.SigninReqDto;
import com.traffic.member.dto.res.SigninResDto;

public interface OAuthApiClient {

    OAuthProvider oAuthProvider();
    SigninResDto requestAccessToken(SigninReqDto reqEntity) throws Exception;
    OAuthInfoResponse requestOauthInfo(String accessToken);

}
