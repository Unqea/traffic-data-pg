package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class TbUser {
    @TableField("username")
    public String username;
    @TableField("password")
    public String password;
}
