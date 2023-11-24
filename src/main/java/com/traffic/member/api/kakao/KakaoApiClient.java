package com.traffic.member.api.kakao;

import com.google.gson.Gson;
import com.traffic.common.enums.OAuthProvider;
import com.traffic.member.api.OAuthApiClient;
import com.traffic.member.api.OAuthInfoResponse;
import com.traffic.member.dto.req.SigninReqEntity;
import com.traffic.member.dto.res.SigninResEntity;
import com.traffic.member.entity.TokenEntity;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class KakaoApiClient implements OAuthApiClient {

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${oauth.kakao.url.auth}")
    private String authUrl;

    @Value("${oauth.kakao.url.api}")
    private String apiUrl;

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${spring.token.url}")
    private String tokenUrl;


    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public SigninResEntity requestAccessToken(SigninReqEntity reqEntity) throws Exception {
        String url = authUrl + "/oauth/token";

        SigninResEntity resEntity = new SigninResEntity();
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("username", reqEntity.getEmail() )
                .add("password", StringUtils.equals("100", reqEntity.getSigninType()) ? reqEntity.getPassword() : reqEntity.getSnsIdentifier())
                .add("signin_type", reqEntity.getSigninType())
                .add("scope", "user")
                .add("grant_type", "password")
                .build();

        // Post 객체 생성
        String authorization = "Basic " + Base64.getEncoder().encodeToString("duotone:duotone".getBytes());
        Request.Builder builder = new Request.Builder().url(tokenUrl)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", authorization)
                .post(requestBody);
        Request request = builder.build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            // 응답 Body
            if(response.body() != null) {
                String body = response.body().string();
                TokenEntity tokenEntity = new Gson().fromJson(body, TokenEntity.class);
                resEntity.setAccess_token(tokenEntity.getAccess_token());
                resEntity.setToken_type(tokenEntity.getToken_type());
                resEntity.setRefresh_token(tokenEntity.getRefresh_token());
                resEntity.setExpires_in(tokenEntity.getExpires_in());
                resEntity.setScope(tokenEntity.getScope());
            } else {
                throw new Exception("Auth server response body null");
            }
        }

        return resEntity;
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl + "/v1/user/me";


        return null;
    }

}
