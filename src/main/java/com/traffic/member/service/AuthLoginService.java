package com.traffic.member.service;

import com.google.gson.Gson;
import com.traffic.member.dto.login_dto.SNSLoginDto;
import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.dto.res.SigninResDto;
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
            String authorizationUrl = snsLoginDto.getAuthorization();
            if (authorizationUrl != null) {
                UriComponents uriComponents = UriComponentsBuilder
                        .fromUriString(authorizationUrl)
                        .queryParam("client_id", snsLoginDto.getId())
                        .queryParam("redirect_uri", URLEncoder.encode(snsLoginDto.getRedirectUri(), "UTF-8"))
                        .queryParam("response_type", "code")
                        .queryParam("state", URLEncoder.encode("1234", "UTF-8"))
                        .build();
                return uriComponents.toString();
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public SigninResDto getAccessToken(SNSLoginDto snsLoginDto, String code) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String tokenUrl = snsLoginDto.getToken(); // 또는 snsLoginDto.getProfile(); 등 사용하는 메서드에 따라 적절히 수정
        if (tokenUrl != null) {
            RequestBody requestBody = new FormBody.Builder()
                    .add("grant_type", "authorization_code")
                    .add("client_id", snsLoginDto.getId())
                    .add("client_secret", snsLoginDto.getSecret())
                    .add("code", code)
                    .add("state", "1234")
                    .build();
            String authorization = "Basic " + Base64.getEncoder().encodeToString("duotone:duotone".getBytes());
            Request.Builder builder = new Request.Builder().url(tokenUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Authorization", authorization)
                    .post(requestBody);
            Request request = builder.build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // 응답 Body
                if (response.body() != null) {
                    String body = response.body().string();
                    SigninResDto resEntity = new Gson().fromJson(body, SigninResDto.class);
                    resEntity.setCode("100");
                    resEntity.setMessage("정상 처리 되었습니다.");
                    // 추가 작업 수행
                    return resEntity;
                }
            }
        }
        return null;
    }
    public SignupReqDto getNaverMemberProfile(SNSLoginDto snsLoginDto, String token) throws IOException {
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
                return new Gson().fromJson(body, SignupReqDto.class);
            }
        }
        return null;
    }
}
