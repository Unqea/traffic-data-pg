package com.traffic.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GeoHashDrawer {

    // Decode Geohash to get coordinates
    public static double[] decodeGeohash(String geohash) {
        final String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        boolean isEven = true;
        double[] lat = {-90.0, 90.0};
        double[] lon = {-180.0, 180.0};

        for (char c : geohash.toCharArray()) {
            int cd = BASE32.indexOf(c);
            for (int j = 0; j < 5; j++) {
                int mask = 1 << (4 - j);
                if (isEven) {
                    refineInterval(lon, (cd & mask) != 0);
                } else {
                    refineInterval(lat, (cd & mask) != 0);
                }
                isEven = !isEven;
            }
        }

        double latitude = (lat[0] + lat[1]) / 2;
        double longitude = (lon[0] + lon[1]) / 2;
        return new double[]{latitude, longitude};
    }

    private static void refineInterval(double[] interval, boolean bit) {
        double mid = (interval[0] + interval[1]) / 2;
        if (bit) {
            interval[0] = mid;
        } else {
            interval[1] = mid;
        }
    }

    // Read GeoJSON file and return as JSONObject
    public static JSONObject readGeoJSON(String filePath) throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        JSONTokener tokener = new JSONTokener(sb.toString());
        return new JSONObject(tokener);
    }

    // Write output to CSV file
    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("geohash,lng,lat\n");
            for (String[] row : data) {
                writer.append(String.join(",", row)).append("\n");
            }
        }
    }

    public static void main(String[] args) throws JSONException {
        try {
            JSONObject geoJson = readGeoJSON("/Users/yin/Downloads/one.geojson");
            JSONArray features = geoJson.getJSONArray("features");

            // Here we would normally generate geohash list from GeoJSON data
            // For simplicity, we'll use a dummy list
            List<String> geohashList = new ArrayList<>();
            geohashList.add("ezs42"); // Example geohash

            List<String[]> outputList = new ArrayList<>();
            for (String geohash : geohashList) {
                double[] coords = decodeGeohash(geohash);
                outputList.add(new String[]{geohash, String.valueOf(coords[1]), String.valueOf(coords[0])});
            }

            writeCSV("/Users/yin/Downloads/dq_geo7.csv", outputList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
