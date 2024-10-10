package com.traffic.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("flow_process")
@ApiModel(value = "FlowProcess对象", description = "流程")
public class FlowProcess {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "流程名称")
    private String flowName;

    @ApiModelProperty(value = "抄送人id(多个可用逗号隔开)")
    private String recipientId;

    @ApiModelProperty(value = "是否可撤回(0:可撤回；1:不可撤回)")
    private Integer withdraw;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "备注")
    private String remark;
}
