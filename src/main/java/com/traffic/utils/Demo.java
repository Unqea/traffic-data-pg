package com.traffic.utils;

public class Demo {
    public static void main(String[] args) {
        String coordinatesString = "120.17329,30.245755;120.17885,30.245471;120.177793,30.242904";
        String[] coordinatesArray = coordinatesString.split(";");

        // 给定的坐标点
        double targetX = 120.180363;
        double targetY = 30.242026;

        // 最小距离和对应的坐标点
        double minDistance = Double.MAX_VALUE;
        String closestPoint = "";

        for (String coordinate : coordinatesArray) {
            String[] parts = coordinate.split(",");
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);

            // 计算欧氏距离
            double distance = Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));

            // 更新最小距离和对应的坐标点
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = coordinate;
            }
        }

        System.out.println("最近的坐标点是：" + closestPoint + "，距离为：" + minDistance);
    }
}
