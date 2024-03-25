package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("uuc_organization")
public class UucOrganization {
    //部门id
    @TableField("guid")
    private String guid;
    //部门名称
    @TableField("org_name")
    private String orgName;
    //父部门id
    @TableField("parent_id")
    private String parentId;
    @TableField(exist = false)
    private List<UucOrganization> list;
}
