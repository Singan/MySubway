package com.traffic.member.api;

import com.google.gson.Gson;
import com.traffic.member.entity.TokenEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "naver.client")

@ConstructorBinding
public class NaverUtil {

    private final String id;
    private final String secret;
    private final String redirectUri;

    public String getNaverAuthorizeUrl() throws UnsupportedEncodingException {

        String secret = getSecret();
        String clientId = getId();
        String redirectUrl = getRedirectUri();

        try {
            UriComponents uriComponents = UriComponentsBuilder
                    .fromUriString("https://nid.naver.com/oauth2.0/authorize")
                    .queryParam("response_type", "code")
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", URLEncoder.encode(redirectUrl, "UTF-8"))
                    .queryParam("state", URLEncoder.encode("1234", "UTF-8"))
                    .build();
            return uriComponents.toString();
        } catch (Exception e) {
            throw e;
        }
    }
    public TokenEntity getNaverAccessToken(String code) throws IOException {

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type","authorization_code")
                .add("client_id",getId())
                .add("client_secret",getSecret())
                .add("code",code)
                .add("state","1234")
                .build();
        String authorization = "Basic " + Base64.getEncoder().encodeToString("duotone:duotone".getBytes());
        Request.Builder builder = new Request.Builder().url("https://nid.naver.com/oauth2.0/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", authorization)
                .post(requestBody);
        Request request = builder.build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            if(response.body() != null) {
                String body = response.body().string();
                TokenEntity tokenEntity = new Gson().fromJson(body, TokenEntity.class);
                return tokenEntity;
            }
        }
        return null;
    }

}
