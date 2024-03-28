package com.traffic;

import java.util.HashMap;
import java.util.Map;

public class Demo2 {

    public static void main(String[] args) {
        String jamPeriod3 = "10:17-21:23,21:34-21:51";
        String s = calculateJamDur(jamPeriod3);
        System.out.println(s);
        //输出结果 0,57
    }

    public static String calculateJamDur(String jamPeriod) {
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(420,540);
        map.put(990,1110);
        for (Integer provide_start : map.keySet()) {
            Integer provide_end = map.get(provide_start);
            int res = 0;
            String[] jamSplit = jamPeriod.split(",");
            for (String per : jamSplit) {
                int start = Integer.parseInt(per.split("-")[0].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[0].split(":")[1]);
                int end = Integer.parseInt(per.split("-")[1].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[1].split(":")[1]);
                //开始和结束都在早高峰
                if ((start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                    res = res + (end - start);
                }
                //开始时间在，结束时间不在
                if ((start >= provide_start && start <= provide_end) && !(end >= provide_start && end <= provide_end)){
                    res = res + (provide_end - start);
                }
                //开始时间不在，结束时间在
                if (!(start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                    res = res + (end - provide_start);
                }
                //开始和结束横跨早高峰
                if (start <= provide_start && end >= provide_end){
                    res = res + (provide_end - provide_start);
                }
            }
            res =  res == 0 ? res : (res == 120 ? res : res + 1);
            result.append(res).append(",");
        }
        return result.substring(0,result.length() - 1);
    }

    public static int calculateMonTrafficJam(String jamPeriod,int provide_start, int provide_end) {
        int res = 0;
        String[] jamSplit = jamPeriod.split(",");
        for (String per : jamSplit) {
            int start = Integer.parseInt(per.split("-")[0].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[0].split(":")[1]);
            int end = Integer.parseInt(per.split("-")[1].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[1].split(":")[1]);
            //开始和结束都在早高峰
            if ((start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                res = res + (end - start);
            }
            //开始时间在，结束时间不在
            if ((start >= provide_start && start <= provide_end) && !(end >= provide_start && end <= provide_end)){
                res = res + (provide_end - start);
            }
            //开始时间不在，结束时间在
            if (!(start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                res = res + (end - provide_start);
            }
            //开始和结束横跨早高峰
            if (start <= provide_start && end >= provide_end){
                res = res + (provide_end - provide_start);
            }
        }
        return res == 0 ? res : (res == 121 ? 120 : res);
    }

    /**
     * 计算分钟数
     * @param time
     * @return
     */
    public static int convertToMinutes(String time) {
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }


}
