package com.traffic.member.service;

import com.traffic.common.config.AuthTokensGenerator;

import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.dto.res.OauthResDto;
import com.traffic.member.entity.Member;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;


    public TokenEntity login(OauthResDto reqDto) {
        //String memberId = findByMember();
        return null;
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


    private String findByMember(SignupReqDto reqDto) {
        return memberRepository.findByMemberEmail(reqDto.getEmail())
                .map(Member::getMemberEmail)
                .orElseGet(() -> newMember(reqDto));
    }

    public TokenEntity newMemberAndLogin(OauthResDto reqDto) {
        Member member = Member.builder()
//                .memberId(reqDto.getId())
//                .memberEmail(reqDto.getEmail())
//                .memberPw(reqDto.getPassword())
//                .memberNm(reqDto.getName())
//                .memberType(reqDto.getMemberType())
                .build();

//        memberRepository.save(member);

        return authTokensGenerator.generate(member.getMemberEmail());
    }

    public boolean memberExists(String email) {
        return memberRepository.findByMemberEmail(email).isPresent();
    }

}
