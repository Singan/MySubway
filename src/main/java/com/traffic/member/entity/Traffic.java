package com.traffic.member.entity;

import com.traffic.common.enums.TrafficType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trafficNo;

    @Enumerated
    private TrafficType trafficType;

    private Long trafficNumber; // 버스 몇번 혹은 지하철 몇 호선
}
