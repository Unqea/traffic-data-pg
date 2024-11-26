package com.traffic.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Geometry {
    private String type = "LineString";
    private List<List<Double>> coordinates;

    public Geometry(String lnglatSeq) {
        this.coordinates = Arrays.stream(lnglatSeq.split(";"))
                                 .map(coord -> {
                                     String[] parts = coord.split(",");
                                     return Arrays.asList(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
                                 })
                                 .collect(Collectors.toList());
    }

    // getters and setters
}