package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ods_new_checkpoint_device_info")
public class OdsNewCheckpointDeviceInfo {
    @TableId(value = "devc_id", type = IdType.AUTO)
    private String devcId;
    private String devcName;
    private String devcAddr;
    private String lng;
    private String lat;
    private String geohash;
    private String boundaryFlag;
    private String isTakeRear;
    private String srcDir;
    private String fDirNo;
    private String tDirNo;
    private String areaCode;
    private String areaName;
    private String dataSrc;
    private String dataVersion;
    private String adcode;
}
