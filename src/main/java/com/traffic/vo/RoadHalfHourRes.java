package com.traffic.vo;
import lombok.Data;

import java.util.List;

@Data
public class RoadHalfHourRes {

    private String code;
    private List<RoadHalfHour> data;
    private String message;
    private String traceId;


}