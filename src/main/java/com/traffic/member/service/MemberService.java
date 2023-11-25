package com.traffic.member.service;

import com.traffic.common.config.AuthTokensGenerator;

import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.entity.Member;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private boolean findOrCreateMember(SignupReqDto reqDto) {
        return memberRepository.existsByMemberEmail(reqDto.getEmail());
    }
    private Optional<Member> findByMember(SignupReqDto reqDto) {
        return memberRepository.findByMemberEmail(reqDto.getEmail());
    }
    public String newMember(SignupReqDto reqDto) {
        Member member = Member.builder()
                .memberEmail(reqDto.getEmail())
                .memberPw(reqDto.getPassword())
                .memberNm(reqDto.getName())
                .memberType(reqDto.getMemberType())
                .build();
        return memberRepository.save(member).getMemberEmail();
    }

    public TokenEntity login(SignupReqDto reqDto) {
        Member member = findByMember(reqDto).orElseThrow(() -> new IllegalArgumentException("no such data"));
        return authTokensGenerator.generate(member.getMemberId());
    }

}
