package com.ahu.coding.beast.training.phase_dp;

/**
 * @author jianzhang
 * 2025/02/20/上午10:42
 * <p>
 * 股票售卖问题 专项
 */
public class Question_StockSell {

    public static void main(String[] args) {

    }

    /**
     * 题目1：
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 分析：
     * 这题非常简单，全程只能买卖各一次是这道题比较简单的关键 直接暴力就有解，参考 getProfit01Stupid 方法
     * 直接暴力虽然可行但是毕竟不优雅 有没有更好的思路呢
     * getProfit02Smart 方法中则假定我每一天都要进行股票售卖，考虑每天的最大收益，效率更高
     */
    public static Integer getProfit01Stupid(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int buy = prices[i];
            for (int j = i; j < prices.length; j++) {
                int sell = prices[j];
                int curProfit = sell - buy;
                if (curProfit > maxProfit) {
                    maxProfit = curProfit;
                }
            }
        }
        return maxProfit;
    }

    public static Integer getProfit01Smart(int[] prices) {

        int minPriceTheDayBefore = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int todayProfit = prices[i] - minPriceTheDayBefore;
            if (todayProfit > maxProfit) {
                maxProfit = todayProfit;
            }

            if (minPriceTheDayBefore > prices[i]) {
                minPriceTheDayBefore = prices[i];
            }
        }

        return maxProfit;

    }


    /**
     * 题目2：
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/">...</a>
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     *
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
     * 最大总利润为 4 + 3 = 7 。
     */
    public static Integer getProfit02(int[] prices) {

        return null;

    }


    /**
     * 每天都有三种「选择」：买入、卖出、无操作，我们用 buy, sell, rest 表示这三种选择。
     * sell 必须在 buy 之后，buy 必须在 sell 之后（第一次除外）。那么 rest 操作还应该分两种状态，一种是 buy 之后的 rest（持有了股票），一种是 sell 之后的 rest（没有持有股票）
     * 这题最难的就是定义DP
     * dp[i][k][0or1]   0<=i<=n-1, 1<=k<=K
     * 比如说 dp[3][2][1] 的含义就是：今天是第三天，我现在手上持有着股票，至今 最多进行 2 次交易。
     * 再比如 dp[2][3][0] 的含义：今天是第二天，我现在手上没有持有股票，至今 最多进行 3 次交易
     * <p>
     * 目标是求解：dp[n - 1][K][0] 就是求解第n-1天 经过K次交易，获得的利润，为什么最后一位是0呢  因为如果最后还持有股票没卖肯定没能获取最大利润
     * <p>
     * 于是我们可以推导出下述状态转移方程
     * <p>
     * dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1] +prices[i])
     * 今天我没有持有股票，有两种可能：
     * - 要么是我昨天就没有持有，然后今天选择rest，所以我今天还是没有持有；
     * - 要么是我昨天持有股票，但是今天我se11了，所以我今天没有持有股票了。
     * <p>
     * dp[i][k][1]=max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
     * 今天我持有着股票，有两种可能：
     * - 要么我昨天就持有着股票，然后今天选择rest，所以我今天还持有着股票；
     * - 要么我昨天本没有持有，但今天我选择buy，所以今天我就持有股票了。
     */
    public static Integer getProfit03(int[] nums, int k) {

        return null;
    }


}
