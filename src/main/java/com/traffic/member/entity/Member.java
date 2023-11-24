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
