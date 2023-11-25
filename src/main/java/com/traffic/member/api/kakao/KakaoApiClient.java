package com.traffic.member.api.kakao;

import com.google.gson.Gson;
import com.traffic.common.enums.OAuthProvider;
import com.traffic.member.dto.req.SigninReqDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.entity.TokenEntity;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class KakaoApiClient  {

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${oauth.kakao.client-id}")
    private String clientId;
    @Value("${oauth.kakao.token.url}")
    private String tokenUrl;
    @Value("${oauth.kakao.redirect.url}")
    private String redirectUrl;
    @Value("${oauth.kakao.client.secret}")
    private String clientSecret;
    @Value("${oauth.kakao.authorization.url}")
    private String authorization;

    public String getKakaoAuthorizeUrl() throws UnsupportedEncodingException {
        try {
            UriComponents uriComponents = UriComponentsBuilder
                    .fromUriString(authorization)
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", URLEncoder.encode(redirectUrl, "UTF-8"))
                    .queryParam("response_type", "code")
                    .build();
            return uriComponents.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public SigninResDto callSignin(SigninReqDto reqEntity, String code) throws Exception {

        SigninResDto resEntity = new SigninResDto();
        resEntity.setCode("100");
        resEntity.setMessage("정상 처리 되었습니다.");

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", GRANT_TYPE)
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("redirect_url", redirectUrl)
                .add("code", code)
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
                resEntity.setAccess_token(tokenEntity.getAccessToken());
                resEntity.setToken_type(tokenEntity.getTokenType());
                resEntity.setRefresh_token(tokenEntity.getRefreshToken());
                resEntity.setExpires_in(tokenEntity.getExpiresIn());
            } else {
                resEntity.setCode("500");
                resEntity.setMessage("서버 장애.");
            }
        }

        return resEntity;
    }



}
