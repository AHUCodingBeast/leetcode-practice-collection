package com.ahu.coding.beast.training.phase_dp;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson2.JSON;

/**
 * @author jianzhang
 * 2025/02/12/上午10:19
 * <p>
 * 题目：给定一个整数数组，找到一个具有最大和的子数组，返回其最大和， 要求时间复杂度O(n)
 * <p>
 * 先澄清一个概念【子数组和子集】
 * 子数组是要求连续的，而子集则只要求元素在目标数组里比如 [1,2,3,4] [2,4] 不是其子数组而是其子集
 * <p>
 * 这种连续的问题一般我们这样定义dp数组
 * dp[i] 表示以第 i 个元素结尾的最大子数组和。
 * 则递推公式为： dp[i] = max{ dp[i-1] + nums[i] , nums[i] }
 *

 */
public class Question20 {
    public static void main(String[] args) {

        int[] nums = {1, -2, 8, -5, 7, 3};

        Pair<Integer, int[]> integerPair = maxSumAndArray(nums);
        System.out.println("最大子数组的和为：" + integerPair.getKey());
        System.out.println("最大子数组为：" + JSON.toJSONString(integerPair.getValue()));


    }

    public static int maxSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty or null");
        }

        // dp[i] 表示以第 i 个元素结尾的最大子数组和。
        // dp[i] = max{ dp[i-1] + nums[i] , nums[i] }

        int[] dp = new int[nums.length];
        int maxVal = -1;

        for (int i = 0; i < nums.length; i++) {
            dp[i] = i == 0 ? 0 : Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > maxVal) {
                maxVal = dp[i];
            }
        }
        return maxVal;
    }

    /**
     * maxSum 代码升级 这个方法不仅打印了最大值还打印了构成最大值的子数组
     */
    public static Pair<Integer, int[]> maxSumAndArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty or null");
        }
        int[] dp = new int[nums.length];

        int begin = 0, end = 0, max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
                //新的起始索引
                begin = i;
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }
            if (dp[i] > max) {
                max = dp[i];
                // 更新结束索引
                end = i;
            }
        }
        int[] temp = new int[end - begin + 1];
        for (int i = begin, k = 0; i <= end; i++) {
            temp[k++] = nums[i];
        }

        return Pair.of(max, temp);

    }


}
