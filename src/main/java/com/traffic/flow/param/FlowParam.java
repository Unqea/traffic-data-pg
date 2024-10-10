package com.traffic.flow.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "FlowParam对象", description = "流程参数")
public class FlowParam {

    @ApiModelProperty(value = "流程名称")
    private String flowName;

    @ApiModelProperty(value = "抄送人id(多个可用逗号隔开)")
    private String recipientId;

    @ApiModelProperty(value = "是否可撤回(0:可撤回；1:不可撤回)")
    private Integer withdraw;

    @ApiModelProperty(value = "流程备注")
    private String remark;

    @ApiModelProperty(value = "该流程下的节点集合")
    private List<FlowNodeParam> nodeList;

}
