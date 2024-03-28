package com.traffic.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoadINdexPeriodByMonthTsVO {
    private String code;
    private List<RoadINdexPeriodByMonthTs> data;
    private String message;
    private String traceId;
}
