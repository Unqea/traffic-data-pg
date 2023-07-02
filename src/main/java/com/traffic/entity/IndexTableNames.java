package com.traffic.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "IndexTableNames对象", description = "表名及表别名")
public class IndexTableNames {
    @ApiModelProperty(value = "表名")
    private String tableName;
    @ApiModelProperty(value = "表别名")
    private String tableComment;
}
