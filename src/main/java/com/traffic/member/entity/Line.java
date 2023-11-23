package com.traffic.member.entity;

import com.traffic.common.enums.WeekType;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "my_line")
@Getter
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
