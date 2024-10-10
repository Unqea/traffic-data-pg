package com.traffic.vo;

import lombok.Data;

@Data
public class RoadHalfHour {

    private String dt;
    private String month;
    private String day_hour;
    private String road_level;
    private String tfcunit_id;
    private String tfcdline_id;
    private String period_type;
    private String date_type_no;

//
//    private String tfcdline_name;
//    private String lnglat_seq;
//    private double avg_speed;
//    private int jam_dur;
//    private int jam_len;
//    private double avg_jam_delay_index;
//    private String data_version;
//    private String adcode;



}