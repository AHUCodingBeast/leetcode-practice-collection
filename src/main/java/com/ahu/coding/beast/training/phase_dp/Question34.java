package com.ahu.coding.beast.training.phase_dp;

/**
 * @author jianzhang
 * 2025/02/20/上午10:24
 */
public class Question34 {

    public static void main(String[] args) {
        int res = climbStairs(10);
        System.out.println(res);
    }

    /**
     * <a href="https://leetcode.cn/problems/climbing-stairs/description/?show=1">...</a>
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 分析：
     * 很容易整理出下面的递推关系：
     * f(n) = f(n-1) + f(n-2)
     * 但是直接递归效率过低存在大量重复计算，所以改为自底向上的动态规划算法来解决
     * dp[i] 表示登上i级台阶的方法总数
     */
    public static int climbStairs(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
