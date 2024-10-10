package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dws_custom_tfcunit_scn_status")
@ApiModel(value = "DwsCustomTfcunitScnStatus对象", description = "自定义区域场景完成状态")
public class DwsCustomTfcunitScnStatus {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "场景标识")
    @TableField("scn_code")
    private String scnCode;

    @ApiModelProperty(value = "场景名称")
    @TableField("scn_name")
    private String scnName;

    @ApiModelProperty(value = "任务id")
    @TableField("task_id")
    private Long taskId;

    @ApiModelProperty(value = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField("gmt_modified")
    private Date gmtModified;

    @ApiModelProperty(value = "运行状态；-2:未运行，-1:失败，0:等待中，1:运行中，2:运行成功")
    @TableField("complete")
    private Integer complete;

    @ApiModelProperty(value = "备注")
    @TableField("message")
    private String message;

    @ApiModelProperty(value = "自定义区域id")
    @TableField("tfcunit_id")
    private String tfcunitId;


    @ApiModelProperty(value = "执行编码")
    @TableField("task_code")
    private String taskCode;

    @ApiModelProperty(value = "日期")
    @TableField("date")
    private String date;

    @ApiModelProperty(value = "日期类型；dt 或 mon")
    @TableField("date_type")
    private String dateType;







}
