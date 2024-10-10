package com.traffic.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("flow_node")
@ApiModel(value = "FlowNode对象", description = "节点")
public class FlowNode {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "流程id")
    private Long processId;

    @ApiModelProperty(value = "节点名称")
    private String nodeName;

    @ApiModelProperty(value = "节点排序id")
    private Long nodeOrderId;

    @ApiModelProperty(value = "节点审批人id(多个可用逗号隔开)")
    private String approverIds;

    @ApiModelProperty(value = "节点审批部门id(多个可用逗号隔开)")
    private String approverDeps;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}
