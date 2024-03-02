package com.traffic.entity;

import lombok.Data;

@Data
public class ChangFa {
    private String 排名;
    private String 道路id;
    private String 名称;
    private String 拥堵时长;
    private String 拥堵指数;
    private String 道路类型;
    private String 成因;
    private String 类别;
}
