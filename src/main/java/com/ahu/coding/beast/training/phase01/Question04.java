package com.ahu.coding.beast.training.phase01;

import cn.hutool.core.util.StrUtil;
import com.ahu.coding.beast.training.phase_dp.Question07;

/**
 * @author jianzhang
 * 2025/01/15/下午2:07
 * <p>
 * 原题： 求解字符串里面的最长回文串，例如 cbbdbbdc  输出 bbdbb
 * <a href="https://leetcode.cn/problems/longest-palindromic-substring/description/">题目链接</a>
 * 这里的
 * 思路1：直接按照动态规划算法处理 solution02  【求解最长回文串相当于求解字符串A和他的逆序串的最长公共子串】
 * 思路2：按照中心拓展法则处理  solution03
 * <p>
 * 最长子序列参见
 * {@link Question07}
 * {@code com.ahu.coding.beast.questionfix.Question07#longestCommonSequence(java.lang.String, java.lang.String)}
 */
public class Question04 {
    public static void main(String[] args) {

        System.out.println(solution03("cbbdbbdc"));
        System.out.println(solution03("abbc"));

    }


    public static String solution03(String str) {

        String maxLengthLoopStr = "";
        // 如果回文串的长度为奇数，则它有一个中心字符；如果回文串的长度为偶数，则可以认为它有两个中心字符
        for (int i = 0; i < str.length() - 1; i++) {
            // 分别考察以 str[i] 为中心和以str[i] 、str[i+1] 为中心拓展而来的回文串长度；
            String res1 = centerExpandLongestLoopStr(str, i, i);
            String res2 = centerExpandLongestLoopStr(str, i, i + 1);

            if (res1.length() > maxLengthLoopStr.length()) {
                maxLengthLoopStr = res1;
            }
            if (res2.length() > maxLengthLoopStr.length()) {
                maxLengthLoopStr = res2;
            }

        }

        return maxLengthLoopStr;
    }

    private static String centerExpandLongestLoopStr(String str, int centerIndex01, int centerIndex02) {
        int i1 = centerIndex01;
        int i2 = centerIndex02;

        while (i1 >= 0 && i2 <= str.length() - 1 && str.charAt(i1) == str.charAt(i2)) {
            i1--;
            i2++;
        }
        // 此时i1和i2指向的元素不同了
        return str.substring(i1 + 1, i2);

    }


    public static String solution02(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        int len = reverseStr.length();
        int[][] dp = new int[len][len];

        int max = 0;
        int maxIndex = -1;

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < reverseStr.length(); j++) {
                if (str.charAt(i) != reverseStr.charAt(j)) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    // 记录下最大公共子串的下标 方便最后输出最长公共子串
                    maxIndex = i;
                }
            }
        }
        // abc 和 abd  max =2  maxIndex 1
        String ret = str.substring(maxIndex - max + 1, maxIndex + 1);

        // 因为要求输出回文字符串最长的公共子串有可能不是回文串
        if (isLoopWords(ret)) {
            return ret;
        }
        return null;

    }


    /**
     * 判断回文串
     *
     * @param str 目标字符串
     * @return 是否是回文
     */
    public static Boolean isLoopWords(String str) {
        if (StrUtil.isEmpty(str)) {
            return false;
        }
        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }


}
