package com.traffic.vo;
import lombok.Data;

import java.util.List;

@Data
public class RoadPeriodTypeRes {

    private String code;
    private List<RoadPeriodType> data;
    private String message;
    private String traceId;


}