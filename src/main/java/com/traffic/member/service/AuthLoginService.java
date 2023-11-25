package com.traffic.member.service;

import com.google.gson.Gson;
import com.traffic.member.dto.login_dto.SNSLoginDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.entity.TokenEntity;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;


@RequiredArgsConstructor
@Service
public class AuthLoginService {

    private final String GRANT_TYPE = "authorization_code";
    private final MemberService memberService;

    public String getAuthorization(SNSLoginDto snsLoginDto) throws UnsupportedEncodingException {
        try {
            UriComponents uriComponents = UriComponentsBuilder
                    .fromUriString(snsLoginDto.getAuthorization())
                    .queryParam("client_id", snsLoginDto.getId())
                    .queryParam("redirect_uri", URLEncoder.encode(snsLoginDto.getRedirectUri(), "UTF-8"))
                    .queryParam("response_type", "code")
                    .queryParam("state", URLEncoder.encode("1234", "UTF-8"))
                    .build();
            return uriComponents.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public SigninResDto getAccessToken(SNSLoginDto snsLoginDto,String code) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type","authorization_code")
                .add("client_id",snsLoginDto.getId())
                .add("client_secret",snsLoginDto.getSecret())
                .add("code",code)
                .add("state","1234")
                .build();
        String authorization = "Basic " + Base64.getEncoder().encodeToString("duotone:duotone".getBytes());
        Request.Builder builder = new Request.Builder().url(snsLoginDto.getToken())
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", authorization)
                .post(requestBody);
        Request request = builder.build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            // 응답 Body
            if(response.body() != null) {
                String body = response.body().string();
                SigninResDto resEntity = new Gson().fromJson(body, SigninResDto.class);
                resEntity.setCode("100");
                resEntity.setMessage("정상 처리 되었습니다.");
                getNaverMemberProfile(snsLoginDto,resEntity.getAccess_token());
                return resEntity;
            }
        }
        return null;
    }
    public String getNaverMemberProfile(SNSLoginDto snsLoginDto,String token) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request.Builder builder = new Request.Builder().url(snsLoginDto.getProfile())
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer "+token)
                .post(requestBody);
        Request request = builder.build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            if(response.body() != null) {
                String body = response.body().string();
            }
        }
        return null;
    }
}
