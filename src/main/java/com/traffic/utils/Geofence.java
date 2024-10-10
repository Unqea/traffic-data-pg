package com.traffic.utils;

public class Geofence {
    private static final double EARTH_RADIUS = 6378137; // meters, WGS84 ellipsoid radius
    private static final double SIDE_LENGTH = 150; // meters

    public static void main(String[] args) {
        String input = "120.135433,30.252867";
        String result = calculateGeofence(input);
        System.out.println(result);
    }

    public static String calculateGeofence(String input) {
        String[] coords = input.split(",");
        double centerLng = Double.parseDouble(coords[0]);
        double centerLat = Double.parseDouble(coords[1]);

        // Convert center point to Mercator coordinates
        double[] centerMercator = latLngToMercator(centerLat, centerLng);

        // Half the side length in meters
        double halfSide = SIDE_LENGTH / 2;

        // Calculate Mercator coordinates for the four corners
        double[][] cornersMercator = new double[4][2];
        cornersMercator[0] = new double[]{centerMercator[0] - halfSide, centerMercator[1] + halfSide}; // Top left
        cornersMercator[1] = new double[]{centerMercator[0] + halfSide, centerMercator[1] + halfSide}; // Top right
        cornersMercator[2] = new double[]{centerMercator[0] + halfSide, centerMercator[1] - halfSide}; // Bottom right
        cornersMercator[3] = new double[]{centerMercator[0] - halfSide, centerMercator[1] - halfSide}; // Bottom left

        // Convert Mercator coordinates back to LatLng
        double[][] cornersLatLng = new double[4][2];
        for (int i = 0; i < 4; i++) {
            cornersLatLng[i] = mercatorToLatLng(cornersMercator[i][0], cornersMercator[i][1]);
        }

        // Construct result string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            result.append(cornersLatLng[i][1]).append(",").append(cornersLatLng[i][0]);
            if (i < 3) {
                result.append(";");
            }
        }

        return result.toString();
    }

    private static double[] latLngToMercator(double lat, double lng) {
        double x = lng * (Math.PI / 180) * EARTH_RADIUS;
        double y = Math.log(Math.tan((Math.PI / 4) + (lat * (Math.PI / 180) / 2))) * EARTH_RADIUS;
        return new double[]{x, y};
    }

    private static double[] mercatorToLatLng(double x, double y) {
        double lng = (x / EARTH_RADIUS) * (180 / Math.PI);
        double lat = (Math.atan(Math.exp(y / EARTH_RADIUS)) - (Math.PI / 4)) * 2 * (180 / Math.PI);
        return new double[]{lat, lng};
    }
}
