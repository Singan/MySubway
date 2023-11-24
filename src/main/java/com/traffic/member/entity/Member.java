package com.traffic.member.entity;

import com.traffic.common.enums.OAuthProvider;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNo;

    private String memberEmail;

    private String memberPw;

    private String memberType;

    private String memberStatus;

    private LocalDate memberRegDt;

    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String email, OAuthProvider oAuthProvider) {
        this.memberEmail = email;
        this.oAuthProvider = oAuthProvider;
    }

}
