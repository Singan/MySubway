package com.traffic.entity;

import com.traffic.dto.enums.TrafficType;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trafficNo;

    @Enumerated
    private TrafficType trafficType;

    private Long TrafficNumber; // 버스 몇번 혹은 지하철 몇 호선
}
