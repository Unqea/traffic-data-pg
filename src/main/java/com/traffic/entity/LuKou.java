package com.traffic.entity;

import lombok.Data;

@Data
public class LuKou {
    private String 路口id;
    private String 路口名称;
    private String 延误指数;
    private String 饱和度;
    private String 延时指数;
    private String 延误等级;
    private String 成因;
    private String 类别;
    private String 成因详情;
}
