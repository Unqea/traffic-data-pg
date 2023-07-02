package com.traffic.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.traffic.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author Yinhenan
 * @date 2023-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TablePropertiesQueryParam对象", description = "查询参数")
public class TablePropertiesQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表的别名")
    private String tableAlias;

    @ApiModelProperty(value = "表的关键词")
    private String tableKeywords;

    @ApiModelProperty(value = "表的产生场景")
    private String tableScenario;

    @ApiModelProperty(value = "更新频率")
    private String updateFrequency;

    @ApiModelProperty(value = "表的描述")
    private String tableDescription;

    @ApiModelProperty(value = "所属库类型")
    private String tableType;
}
