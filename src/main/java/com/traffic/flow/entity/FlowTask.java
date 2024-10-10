package com.traffic.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("flow_task")
@ApiModel(value = "FlowTask对象", description = "任务")
public class FlowTask {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "流程id")
    private Long processId;

    @ApiModelProperty(value = "当前节点id")
    private Long nodeId;

    @ApiModelProperty(value = "发起人id")
    private Long initUser;

    @ApiModelProperty(value = "处理人id(多个可用逗号隔开)")
    private String handleUser;

    @ApiModelProperty(value = "处理部门id(多个用逗号隔开)")
    private String handleDep;

    @ApiModelProperty(value = "处理状态")
    private Integer handleStatus;

    @ApiModelProperty(value = "处理意见")
    private String handleView;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "备注")
    private String remark;
}
