package com.traffic.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DwsHzjjzdMonitoryPoint {
    private Long smid;
    private String smuserid;
    private String smgeometry;
    private String objectidOne;
    private String id;
    private String objectid;

    @ApiModelProperty(value = "监控编号")
    private String indexCode;

    private String regionIndexCode;
    private String name;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    private String cameraType;
    private String resourceType;
    private String regionName;
    private String remark;
    private String createTime;
    private String updateTime;
    private String title;


}