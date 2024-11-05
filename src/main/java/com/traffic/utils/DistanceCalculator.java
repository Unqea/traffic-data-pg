package com.traffic.utils;

public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6378137; // WGS84地球半径，单位为米


    /**
     * 计算两个经纬度坐标之间的距离
     *
     * @param lngLat1 第一个点的经纬度
     * @param lngLat2 第一个点的纬纬度
     *
     * @return 距离，单位为米
     */
    public static double calculateDistance(String lngLat1, String lngLat2) {
        double lng1 = Double.parseDouble(lngLat1.split(",")[0]);
        double lat1 = Double.parseDouble(lngLat1.split(",")[1]);

        double lng2 = Double.parseDouble(lngLat2.split(",")[0]);
        double lat2 = Double.parseDouble(lngLat2.split(",")[1]);

        // 将经纬度从度转换为弧度
        double radLat1 = lat1 * Math.PI / 180.0;
        double radLat2 = lat2 * Math.PI / 180.0;
        double deltaLat = (lat1 - lat2) * Math.PI / 360.0;
        double deltaLng = (lng1 - lng2) * Math.PI / 360.0;

        // 使用给定公式计算距离
        double a = Math.pow(Math.sin(deltaLat), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng), 2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS * c;
    }


    public static void main(String[] args) {
        double distance = calculateDistance("119.445929,29.926343", "119.42953,29.921189");
        System.out.println("Distance between points: " + distance + " meters");
    }
}
