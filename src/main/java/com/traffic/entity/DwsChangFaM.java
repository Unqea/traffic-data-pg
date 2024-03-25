package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dws_chang_fa_m")
public class DwsChangFaM {
    private String 排名;
    private String 道路id;
    private String 名称;
    private String 拥堵时长;
    private String 拥堵指数;
    private String 道路类型;
    private String 成因;
}
