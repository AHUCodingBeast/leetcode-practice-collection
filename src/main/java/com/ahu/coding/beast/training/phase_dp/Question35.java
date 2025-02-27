package com.ahu.coding.beast.training.phase_dp;

/**
 * @author jianzhang
 * 2025/02/24/下午4:01
 * <p>
 */
public class Question35 {
    public static void main(String[] args) {

        System.out.println(longestIncrSequence(new int[]{10, 4, 20, 10, 15, 13}));
        System.out.println(longestIncrSequence(new int[]{1, 4, 20, 10, 15, 13}));

        System.out.println(longestIncrSubArray(new int[]{10, 4, 20, 50, 15, 13}));
        System.out.println(longestIncrSubArray(new int[]{1, 4, 20, 21, 10, 15, 13}));
    }

    /**
     * 题目： 已知一个整数数组nums 请你给出他的最长递增子序列的长度
     */
    public static int longestIncrSequence(int[] nums) {
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

    /**
     * 题目： 已知一个整数数组nums 请你给出他的最长递增子数组的长度
     */
    public static int longestIncrSubArray(int[] nums) {
        // dp[i] 以nums[i] 结尾的最长递增子数组长度
        //dp[i] = dp[i-1]+1  nums[i] > nums[i-1]
        //dp[i] = 1 nums[i] <= nums[i-1]
        int[] dp = new int[nums.length];
        int maxlen = 0;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            if (dp[i] > maxlen) {
                maxlen = dp[i];
            }
        }
        return maxlen;
    }

}
