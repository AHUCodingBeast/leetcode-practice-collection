package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/02/25/下午2:59
 * <p>
 * 把M个同样的苹果放在N个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？注：5，1，1和1，5，1 是同一种分法。
 * 每个用例包含二个整数M和N。0<=m<=10，1<=n<=10。（盘子至少有一个）
 */
public class Question39 {


    public static int countWays(int m, int n) {
        // 当苹果数量为 0 或者盘子数量为 1 时，只有一种分法
        if (m == 0 || n == 1) {
            return 1;
        }
        // 如果盘子数量大于苹果数量，多余的盘子不会影响分法数量
        if (n > m) {
            return countWays(m, m);
        } else {
            // 情况一：至少有一个盘子为空，相当于把 m 个苹果放在 n - 1 个盘子里
            // 情况二：所有盘子都不为空，先每个盘子放一个苹果，再把剩下的 m - n 个苹果放在 n 个盘子里
            return countWays(m, n - 1) + countWays(m - n, n);
        }
    }

    public static void main(String[] args) {
        // 苹果的数量
        int m = 5;
        // 盘子的数量
        int n = 3;
        int ways = countWays(m, n);
        System.out.println("将 " + m + " 个苹果放在 " + n + " 个盘子里的不同分法有：" + ways + " 种。");
    }


}
