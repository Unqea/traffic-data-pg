package com.traffic.entity;

import lombok.Data;

@Data
public class JingQu {
    private String 序号;
    private String 道路id;
    private String 道路名称;
    private String 道路类型;
    private String 拥堵指数;
    private String 是否常发拥堵是则拥堵时长;
    private String 成因;
    private String 类别;
}
