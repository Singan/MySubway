package com.traffic.member.entity;

import com.traffic.common.enums.OAuthProvider;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNo;

    private String memberEmail;

    private String memberPw;

    private String memberNm;

    private String memberType;

    private String mobileNo;

    private String memberStatus;

    private LocalDate memberRegDt;


    @Builder
    public Member(String email, String memberPw, String memberNm, String memberType, String mobileNo) {
        this.memberEmail = email;
        this.memberPw = memberPw;
        this.memberNm = memberNm;
        this.memberType = memberType;
        this.mobileNo = mobileNo;
    }

}
