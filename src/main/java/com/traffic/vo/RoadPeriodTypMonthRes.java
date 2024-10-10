package com.traffic.vo;
import lombok.Data;

import java.util.List;

@Data
public class RoadPeriodTypMonthRes {

    private String code;
    private List<RoadPeriodTypeMonth> data;
    private String message;
    private String traceId;


}