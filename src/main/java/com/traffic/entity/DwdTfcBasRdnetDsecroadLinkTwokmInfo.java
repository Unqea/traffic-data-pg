package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DwdTfcBasRdnetDsecroadLinkTwokmInfo {
    private String dsecroadId;
    private String dsecroadName;
    @TableField("dsecroad_dir_4_no")
    private String dsecroadDir4No;
    private String dsecroadLen;
    private String dsecroadLevel;
    private String startCrossId;
    private String startLng;
    private String startLat;
    private String startGeohash;
    private String endCrossId;
    private String endLng;
    private String endLat;
    private String endGeohash;
    private String interSeq;
    private String pRidSeq;
    private String collectionRidList;
    private String lnglatSeq;
    private String startCrossName;
    private String endCrossName;
    private String laneCnt;
    private String centPoint;
    private String linkDsecroadId;
    private String dataVersion;
    private String adcode;
}


