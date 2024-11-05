package com.traffic.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CameraIndex {
    @ApiModelProperty(value = "监控编码")
    private String indexCode;
    @ApiModelProperty(value = "监控名称")
    private String cameraName;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
}
