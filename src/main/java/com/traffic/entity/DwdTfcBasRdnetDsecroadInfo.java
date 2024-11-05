package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@TableName("dwd_tfc_bas_rdnet_dsecroad_info")
@Data
public class DwdTfcBasRdnetDsecroadInfo {

    @ApiModelProperty(value = "断面分方向rid")
    private String dsecroadId;

    @ApiModelProperty(value = "断面分方向rid道路名称")
    private String dsecroadName;

    @TableField("dsecroad_dir_no")
    @ApiModelProperty(value = "起点路口→终点路口的矢量,相对于正北的角度顺时针方向角度，1、南向北（>315或<=45）2、西向东（>45且<=135）3、北向南（>135且<=225）4、东向西（>225且<=315）")
    private Long dsecroadDirNo;

    @ApiModelProperty(value = "道路长度")
    private Double dsecroadLen;

    @ApiModelProperty(value = "道路等级,取长度占比最高的道路等级")
    private String dsecroadLevel;

    @ApiModelProperty(value = "分方向道路起点路口")
    private String startCrossId;

    @ApiModelProperty(value = "分方向道路起点坐标")
    private Double startLng;

    @ApiModelProperty(value = "分方向道路起点坐标")
    private Double startLat;

    @ApiModelProperty(value = "分方向道路起点的geohash")
    private String startGeohash;

    @ApiModelProperty(value = "分方向道路中终点路口")
    private String endCrossId;

    @ApiModelProperty(value = "分方向道路终点坐标")
    private Double endLng;

    @ApiModelProperty(value = "分方向道路终点坐标")
    private Double endLat;

    @ApiModelProperty(value = "分方向道路终点的geohash")
    private String endGeohash;

    @ApiModelProperty(value = "路口id序列(有序)")
    private String interSeq;

    @ApiModelProperty(value = "主路rid的序列(有序)")
    private String pRidSeq;

    @ApiModelProperty(value = "dsectionchl包含的所有rid集合，格式为#分割主路组，&分割同组内的主辅路： 主路1$辅路1,辅路2#主路2$辅路1,辅路2")
    private String collectionRidList;

    @ApiModelProperty(value = "主路道路经纬度串")
    private String lnglatSeq;

    @ApiModelProperty(value = "起点路口名称")
    private String startCrossName;

    @ApiModelProperty(value = "终点路口名称")
    private String endCrossName;

    @ApiModelProperty(value = "车道数量，按组成rid长度最大的车道数量核算")
    private Long laneCnt;

    @ApiModelProperty(value = "路网版本")
    private String dataVersion;

    @ApiModelProperty(value = "项目编码")
    private String adcode;
}
