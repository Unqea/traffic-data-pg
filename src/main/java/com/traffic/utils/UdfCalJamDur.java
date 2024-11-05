package com.traffic.utils;

import java.util.ArrayList;
import java.util.List;

public class UdfCalJamDur {

    public static void main(String[] args) {
        String str = "07:12-10:59,11:21-11:48,12:37-19:55,20:44-21:06";
        String evaluate = evaluate(str);
        System.out.println(evaluate);
    }

    public static String evaluate(String jamPeriod) {
        List<String> result = new ArrayList<>();
        int[][] timeRanges = {{420, 540}, {990, 1110}, {541, 989}, {0, 1440}};

        for (int[] timeRange : timeRanges) {
            int res = 0;
            String[] periods = jamPeriod.split(",");
            for (String per : periods) {
                String[] startEnd = per.split("-");
                String[] startTimeParts = startEnd[0].split(":");
                String[] endTimeParts = startEnd[1].split(":");
                int start = Integer.parseInt(startTimeParts[0]) * 60 + Integer.parseInt(startTimeParts[1]);
                int end = Integer.parseInt(endTimeParts[0]) * 60 + Integer.parseInt(endTimeParts[1]);

                if (timeRange[0] <= start && start <= timeRange[1] && timeRange[0] <= end && end <= timeRange[1]) {
                    res += end - start;
                } else if (timeRange[0] <= start && start <= timeRange[1] && timeRange[0] <= end && end > timeRange[1]) {
                    res += timeRange[1] - start;
                } else if (timeRange[0] <= start && start > timeRange[1] && timeRange[0] <= end && end <= timeRange[1]) {
                    res += end - timeRange[0];
                } else if (start <= timeRange[0] && end >= timeRange[1]) {
                    res += timeRange[1] - timeRange[0];
                }

                res = res == 0 ? res : (res == 120 ? res : res + 1);
            }

            result.add(String.valueOf(res));
        }

        return String.join(",", result);
    }
}