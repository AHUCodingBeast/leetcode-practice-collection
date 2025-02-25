package com.ahu.coding.beast.training.phase_dp;

/**
 * @author jianzhang
 * 2025/02/24/下午4:01
 * <p>
 * 已知一个整数数组nums 请你给出他的最长递增子序列的长度 (LIS 问题)
 */
public class Question35 {
    public static void main(String[] args) {

        System.out.println(handle(new int[]{10,4,20,10,15,13}));
        System.out.println(handle(new int[]{1,4,20,10,15,13}));
    }

    public static int handle(int[] nums) {
        // dp[i] 前i+1个数字的最长公共子序列的长度
        //dp[i] = dp[i-1]+1  nums[i] > nums[i-1]
        //dp[i] = dp[i-1]  nums[i] <= nums[i-1]

        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[nums.length - 1];
    }


}
