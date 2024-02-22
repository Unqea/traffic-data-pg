package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("excel_bean")
public class ExcelBean {
    //一级
    private String one;

    //二级
    private String two;

    //三级
    private String three;

    //序号
    private String number;

    //名称
    private String name;

    //编码
    private String code;

    //备注
    private String notes;

}
