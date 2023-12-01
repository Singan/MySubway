package com.traffic.member.service;

import com.traffic.common.config.AuthTokensGenerator;

import com.traffic.member.dto.req.SignupReqDto;
import com.traffic.member.dto.res.OauthResDto;
import com.traffic.member.dto.res.SigninResDto;
import com.traffic.member.dto.res.SignupResDto;
import com.traffic.member.entity.Member;
import com.traffic.member.entity.TokenEntity;
import com.traffic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;


    public SigninResDto login(SignupReqDto reqDto) {
        SigninResDto resDto = new SignupResDto();
        resDto.setResultCode("100");
        resDto.setMessage("정상 처리 되었습니다.");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(reqDto.getPassword());

        Member member = memberRepository.findMemberByMemberEmail(reqDto.getEmail());

        if (StringUtils.equals("100", reqDto.getMemberType())) {
            if (member != null) {
               // 토큰 생성

            } else {
                resDto.setResultCode("200");
                resDto.setMessage("로그인 실패");
            }
        }

        return resDto;
    }

    public SignupResDto newMember(SignupReqDto reqDto) {
        SignupResDto resDto = new SignupResDto();
        resDto.setResultCode("100");
        resDto.setMessage("정상 처리 되었습니다.");

        Member member = Member.builder()
                .memberEmail(reqDto.getEmail())
                .memberPw(new BCryptPasswordEncoder().encode(reqDto.getPassword()))
                .memberNm(reqDto.getName())
                .memberRegDt(LocalDate.now())
                .memberStatus("100")
                .memberType("normal")
                .build();

        // 이메일 중복체크
        if (StringUtils.equals("100", reqDto.getMemberType())) {
            Optional<Member> checkMember = memberRepository.findByMemberEmail(reqDto.getEmail());
            if (checkMember.isPresent()) {
                resDto.setResultCode("200");
                resDto.setMessage("이미 가입한 회원입니다.");
            }
            return resDto;
        }

        memberRepository.save(member);
        return resDto;
    }

    public TokenEntity newMemberAndLogin(OauthResDto reqDto) {
        Member member = Member.builder()
                .memberId(reqDto.getId())
                .memberEmail(reqDto.getEmail())
                .memberPw("")
                .memberNm(reqDto.getNickname())
                .memberRegDt(LocalDate.now())
                .memberStatus("100")
                .memberType(reqDto.getType())
                .build();

        memberRepository.save(member);

        return authTokensGenerator.generate(member.getMemberEmail());
    }

    public boolean memberExists(String email) {
        return memberRepository.findByMemberEmail(email).isPresent();
    }

}
