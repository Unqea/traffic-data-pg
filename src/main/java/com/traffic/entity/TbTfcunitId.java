package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_tfcunitId")
public class TbTfcunitId {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("tfcunitId")
    private String tfcunitId;

    @TableField("create_time")
    private Date createTime;

    @TableField("stu")
    private int stu;
}
