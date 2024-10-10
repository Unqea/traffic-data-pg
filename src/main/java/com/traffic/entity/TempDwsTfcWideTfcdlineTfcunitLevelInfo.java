package com.traffic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("temp_dws_tfc_wide_tfcdline_tfcunit_level_info")
public class TempDwsTfcWideTfcdlineTfcunitLevelInfo {
    private String tfcdlineId;
    private String tfcdlineName;
    private Double len;
    private String tfcunitId;
    private String roadLevel;
    private String lnglatSeq;
    private String indexCode;
    private String longitude;
    private String latitude;
    private String dataVersion;

}
