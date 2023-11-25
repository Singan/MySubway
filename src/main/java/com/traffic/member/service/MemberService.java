package com.traffic.member.service;

import com.traffic.common.config.AuthTokensGenerator;
import com.traffic.member.api.NaverUtil;
import com.traffic.member.api.kakao.KakaoApiClient;
import com.traffic.member.dto.req.SigninReqDto;
import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.entity.Member;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final NaverUtil naverUtil;
    private final KakaoApiClient apiClient;
    private final AuthTokensGenerator authTokensGenerator;

    public String getNaverAuthorizeUrl() throws UnsupportedEncodingException {
            return naverUtil.getNaverAuthorizeUrl();
    }
    public TokenEntity getAccessToken(String code) throws IOException {
        return naverUtil.getNaverAccessToken(code);
    }

    public String getKakaoLogin() throws UnsupportedEncodingException {
        return apiClient.getKakaoAuthorizeUrl();
    }

    public SigninResDto kakaoLogin(SigninReqDto reqEntity, String code) throws Exception {
          return apiClient.callSignin(reqEntity, code);
    }

    private String findOrCreateMember(SignupReqDto reqDto) {
        return memberRepository.findByEmail(reqDto.getEmail())
                .map(Member::getMemberEmail)
                .orElseGet(() -> newMember(reqDto));
    }

    private String newMember(SignupReqDto reqDto) {
        Member member = Member.builder()
                .memberEmail(reqDto.getEmail())
                .memberPw(reqDto.getPassword())
                .memberNm(reqDto.getName())
                .memberType(reqDto.getMemberType())
                .mobileNo(reqDto.getMobileNo())
                .build();

        return memberRepository.save(member).getMemberEmail();
    }

    public TokenEntity login(SignupReqDto reqDto) {
        String memberId = findOrCreateMember(reqDto);
        return authTokensGenerator.generate(memberId);
    }

}
