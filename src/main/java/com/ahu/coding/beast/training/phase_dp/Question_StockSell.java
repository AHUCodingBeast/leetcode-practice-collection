package com.ahu.coding.beast.training.phase_dp;

/**
 * @author jianzhang
 * 2025/02/20/上午10:42
 * <p>
 * 股票售卖问题 专项
 */
public class Question_StockSell {

    public static void main(String[] args) {

        int[] price = {7,1,5,3,6,4};

        System.out.println(getProfit01Stupid(price));
        System.out.println(getProfit01Smart(price));

        System.out.println(getProfit02Greedy(price));
        System.out.println(getProfit02Dp(price));


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
            // 一次遍历的过程中就可以记录之前最低价格的信息
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
     * <p>
     * 简单来说这题就是升级了 股票可以多次买卖，但是手里始终只能持有一股
     * <p>
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
     * 最大总利润为 4 + 3 = 7 。
     */
    public static Integer getProfit02Greedy(int[] prices) {
        // 贪心思路：
        // 假设我总是今天买明天卖 设今天价格p1 明天价格为p2 则利润为 p2-p1
        // 假设股票一直上涨 肯定是第一天买最后一天卖划算  利润为pn-p1 这种方式等价于 每天都在买卖 即pn-p1= (p2-p1)+(p3-p2)+(p4-p3)+....+(pn-pn-1)
        // 假设股票今天和明天的价格趋势是下跌趋势只要不买卖就行 所以我们两天两天的去考虑 如果价格减出来是负数就舍弃利润算0
        // 思路参考：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solutions/1/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) {
                profit += tmp;
            }
        }
        return profit;
    }

    public static Integer getProfit02Dp(int[] prices) {
        // 动态规划
        // dp[i][0]表示第i天交易完后手里没有股票的最大利润，dp[i][1]表示第i天交易完后手里持有一支股票的最大利润（i从0开始）。
        // dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]} 昨天就没有股票 或者 昨天卖了导致今天没有
        // dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]} 昨天也持有股票 或者 昨天没有但是后来新买了一个导致今天有了股票
        int n = prices.length;
        int[][] dp = new int[n][2];
        // 第0天不做交易
        dp[0][0] = 0;
        // 如果第0天 持有股票说明第0天买了 这里初始化要成负数
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 为什么返回 dp[n - 1][0]; 因为dp[n - 1][1] 一定比dp[n - 1][0] 要小
        return dp[n - 1][0];
    }


}
