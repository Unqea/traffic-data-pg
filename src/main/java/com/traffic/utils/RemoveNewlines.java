package com.traffic.utils;

public class RemoveNewlines {
    public static String removeNewlines(String input) {
        return input.replaceAll("\\n", "");
    }

    public static void main(String[] args) {
        String input = "";
        String result = removeNewlines(input);
        System.out.println("去掉换行后的字符串：" + result);
    }
}
