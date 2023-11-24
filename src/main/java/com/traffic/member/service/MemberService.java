package com.traffic.member.service;

import com.traffic.member.api.NaverUtil;
import com.traffic.member.api.OAuthInfoResponse;
import com.traffic.member.api.kakao.KakaoApiClient;
import com.traffic.member.entity.Member;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final NaverUtil naverUtil;

    private final KakaoApiClient apiClient;

    public String getNaverAuthorizeUrl() throws UnsupportedEncodingException {
            return naverUtil.getNaverAuthorizeUrl();
    }
    public TokenEntity getAccessToken(String code) throws IOException {
        return naverUtil.getNaverAccessToken(code);
    }


    private String newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getMemberEmail();
    }
}
