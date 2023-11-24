package com.traffic.member.service;

import com.traffic.member.api.NaverUtil;
import com.traffic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final NaverUtil naverUtil;

    public String getNaverAuthorizeUrl() throws

            UnsupportedEncodingException {

        String secret = naverUtil.getSecret();
        String clientId = naverUtil.getId();
        String redirectUrl = naverUtil.getRedirectUri();
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
            System.out.println(e);
            throw e;
        }
    }
}
