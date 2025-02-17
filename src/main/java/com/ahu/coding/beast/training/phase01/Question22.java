package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/02/13/上午10:23
 *
 * <a href="https://leetcode.cn/problems/fibonacci-number/description/">...</a> 斐波那契数列
 * <p>
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 */
public class Question22 {

    public static void main(String[] args) {

    }

    /**
     * 递归写法，其实运用了分解问题的思想,但是带来了大量重复计算
     */
    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 迭代的方式去写，这时候启用了一个数组做记录
     */
    public static int fibIteration(int n) {
        if (n < 2) {
            return n;
        }
        int[] record = new int[n];
        record[0] = 0;
        record[1] = 1;
        for (int i = 2; i <= n; i++) {
            record[i] = record[i - 1] + record[i - 2];
        }
        return record[n];
    }


    /**
     * 动态规划的写法则是从基础场景开始一致推到目标值 但是这里比迭代更有优势的地方是只使用了三个变量值，而不是用数组去记录
     * <p>
     * [p,q,r]
     */
    public static int fibDp(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


}
