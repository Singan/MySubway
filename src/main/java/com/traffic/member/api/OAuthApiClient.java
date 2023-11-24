package com.traffic.member.api;

import com.traffic.common.enums.OAuthProvider;
import com.traffic.member.dto.req.SigninReqEntity;
import com.traffic.member.dto.res.SigninResEntity;

import java.io.IOException;

public interface OAuthApiClient {

    OAuthProvider oAuthProvider();
    SigninResEntity requestAccessToken(SigninReqEntity reqEntity) throws Exception;
    OAuthInfoResponse requestOauthInfo(String accessToken);

}
