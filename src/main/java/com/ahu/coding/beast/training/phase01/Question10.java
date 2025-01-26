package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/14/下午5:19
 * 题目：
 * 假定现在有 1元 5元 10元 20元 50元 100元 6种面值的人民币
 * 每种人民币对应的数量分别为 10,5,1,2,3,4
 * 现在给定一个正整数 n (n<=500) 请你给最少个数的钱币组合，如果无法组成目标金额则程序输出-1
 * <p>
 * 例如给定 352 应该输出6  3个100 1张50 2张1元 共计6张
 *
 * <p>
 * 问题分析
 * <b>
 * dp[i][j] 表示使用前 i 种面额的人民币，凑出金额 j 所需的最少钱币个数。如果无法凑出金额 j，则 dp[i][j] 的值为正无穷大。
 * </b>
 * 当不使用任何钱币时，凑出金额 0 的最少钱币个数为 0，即 dp[0][0] = 0。
 * dp[i][j] = dp[i - 1][j]  不使用第i种面额的人民币
 * 当使用第i种面额的人民币时。假定使用的次数为 k ,那么最终的钱币个数就相当于用前i-1种钱币去组j-k*value[i] 的钱币个数加上 k
 * 那么就相当于 dp[i][j] = dp[i-1][j- k * value[i]] + k
 */
public class Question10 {
    public static void main(String[] args) {
        composeTargetMoney(new int[]{1, 5, 10, 20, 50, 100}, new int[]{10, 5, 1, 2, 3, 4}, 352);
    }


    public static void composeTargetMoney(int[] faceVal, int[] count, int targetValue) {
        // 都选择+1 是为了让dp数组从逻辑意义上的1 开始计数
        int[][] dp = new int[faceVal.length + 1][targetValue + 1];

        // path[i][j] 代表使用第i种面值组成金额j，所需要的纸币数目
        int[][] path = new int[faceVal.length + 1][targetValue + 1];

        dp[0][0] = 0;

        for (int i = 1; i < dp.length; i++) {
            // 用前i种钱币去组0元,只要一张都不选就行。钱币总数为0
            dp[i][0] = 0;
        }

        for (int j = 1; j < dp[0].length; j++) {
            // 没有任何钱币但是想去组j元 是不可能实现的，需要钱币总数设置为正无穷
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int k = 0;
                // 不用第i种钱币去组目标金额
                dp[i][j] = dp[i - 1][j];
                while (k <= count[i - 1] && j - k * faceVal[i - 1] >= 0) {
                    // 防止溢出的判断 dp[i - 1][j - k * faceVal[i - 1]] + k 有可能溢出
                    if (dp[i - 1][j - k * faceVal[i - 1]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * faceVal[i - 1]] + k);
                        path[i][j] = k;
                    }
                    k++;
                }
            }
        }

        if (dp[faceVal.length][targetValue] == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println("最少需要" + dp[faceVal.length][targetValue] + "张纸币");
            int i = faceVal.length;
            int j = targetValue;
            StringBuilder res = new StringBuilder();
            while (i >= 0 && j >= 0) {
                if (path[i][j] != 0) {
                    res.append(faceVal[i - 1]).append(":").append(path[i][j]).append("张").append("\n");
                    // j代表需要组的金额总数 这里选了path[i][j] 张faceVal[i-1]的纸币，所以这里要扣减继续回溯
                    j = j - faceVal[i - 1] * path[i][j];
                }
                i--;
            }
            System.out.println(res);
        }


    }


}
