package com.traffic;

public class LeeCode {
    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        int[] ints = twoSum(nums, target);
        System.out.println(ints[0] + "," + ints[1]);
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
