package com.traffic.member.entity;

import com.traffic.common.enums.WeekType;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "my_line")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LineNo;

    @JoinColumn(name = "member_no",nullable = false)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Member member;

    private String depart;

    private Time rideTime;

    @Enumerated(EnumType.STRING)
    private WeekType rideWeek;


}
