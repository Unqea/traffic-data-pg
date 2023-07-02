package com.traffic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author Yinhenan
 * @date 2023-06-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "TablePropertiesQueryVo对象", description = "查询参数")
public class TablePropertiesQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表的别名")
    private String tableAlias;

    @ApiModelProperty(value = "表的关键词")
    private String tableKeywords;

    @ApiModelProperty(value = "表的产生场景")
    private String tableScenario;

    @ApiModelProperty(value = "表的上游表")
    private String upstreamTables;

    @ApiModelProperty(value = "更新频率")
    private String updateFrequency;

    @ApiModelProperty(value = "表的描述")
    private String tableDescription;

    @ApiModelProperty(value = "所属库类型")
    private String tableType;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}