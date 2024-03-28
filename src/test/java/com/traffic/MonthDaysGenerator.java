package com.traffic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MonthDaysGenerator {
    public static String generateDaysInMonth(String month) {
        StringBuilder result = new StringBuilder();
        
        // 解析传入的月份字符串
        LocalDate date = LocalDate.parse(month + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        // 获取该月份的天数
        int daysInMonth = date.lengthOfMonth();
        
        // 生成每天的日期并添加到结果字符串中
        for (int i = 1; i <= daysInMonth; i++) {
            result.append(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            if (i < daysInMonth) {
                result.append(",");
            }
            date = date.plusDays(1); // 递增日期到下一天
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        String month = "202312"; // 输入月份格式(yyyyMM)
        String daysInMonth = generateDaysInMonth(month);
        System.out.println(daysInMonth);
    }
}
