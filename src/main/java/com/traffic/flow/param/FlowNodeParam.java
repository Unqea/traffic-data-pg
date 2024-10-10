package com.traffic.flow.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FlowNodeParam对象", description = "节点参数")
public class FlowNodeParam {

    @ApiModelProperty(value = "节点名称")
    private String nodeName;

    @ApiModelProperty(value = "节点排序id")
    private Long nodeOrderId;

    @ApiModelProperty(value = "节点审批人id(多个可用逗号隔开)")
    private String approverIds;

    @ApiModelProperty(value = "节点审批部门id(多个可用逗号隔开)")
    private String approverDeps;

    @ApiModelProperty(value = "节点备注")
    private String remark;
}
