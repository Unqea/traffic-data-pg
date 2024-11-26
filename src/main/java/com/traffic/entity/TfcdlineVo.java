package com.traffic.entity;

import lombok.Data;

@Data
public class TfcdlineVo {
    private String id;
    private String level;
    private String name;
    private String lnglatSeq;
    private double len;
    private Geometry geometry;

    // getters and setters
}


