package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tfcdline_result_traffic")
public class TfcdlineResultTraffic {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String tfcdlineId;
    private String tfcdlineName;
    private String roadType;
    private String jamDur;
    private String jamPeriod;
    private String avgJamDelayIndex;
    private String flow;
    private String avgSpeed;


    private String monJamIndex;
    private String monFlow;
    private String monSpeed;
    private String monJamDur;


    private String eveJamIndex;
    private String eveFlow;
    private String eveSpeed;
    private String eveJamDur;
    private String reson;

}
