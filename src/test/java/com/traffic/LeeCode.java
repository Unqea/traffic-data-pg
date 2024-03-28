package com.traffic;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class LeeCode {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (Integer num : integers) {
            stringJoiner.add(String.valueOf(num));
        }

        String s = stringJoiner.toString();
        System.out.println(s);

        String str = "hello";
        String a = "a";
        String res = str + a;

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] ints = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < target; j++) {
                if (nums[i] > target || nums[j] > target){
                    break;
                }else if (i == j){
                    break;
                }else if (nums[i] + nums[j] == target){
                    ints[0] = j;
                    ints[1] = i;
                    break;
                }
            }
        }
        return ints;
    }
}
