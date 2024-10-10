package com.traffic.vo;
import lombok.Data;

import java.util.List;

@Data
public class RoadHalfHourMonthRes {

    private String code;
    private List<RoadHalfHourMonth> data;
    private String message;
    private String traceId;


}