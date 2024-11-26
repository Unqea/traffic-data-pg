package com.traffic.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Tfcdline {
    private String id;
    private String level;
    private String name;
    private double len;
    private Geometry geometry;

    // getters and setters
}


