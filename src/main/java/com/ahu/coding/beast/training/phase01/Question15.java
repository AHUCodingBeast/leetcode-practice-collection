package com.ahu.coding.beast.training.phase01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianzhang
 * 2025/02/10/上午10:39
 * {@link Question03}
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
public class Question15 {


    public static void main(String[] args) {
        System.out.println(containArrangement1("ab", "eidbaooo"));
        System.out.println(containArrangement1("ab", "eidboaoo"));
    }

    public static Boolean containArrangement2(String s1, String s2) {



        return false;

    }

    /**
     * 解法1：暴力求解法：直接求解第一个字符串的所有全排列，然后去判断第二个字符串是否包含它
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 题解
     */
    public static Boolean containArrangement1(String s1, String s2) {
        List<String> strAllArrangement = getStrAllArrangement(s1);
        for (String str : strAllArrangement) {
            if (s2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getStrAllArrangement(String str) {
        List<CharWrapper> charWrapperArrayList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            charWrapperArrayList.add(new CharWrapper(str.charAt(i), false));
        }
        List<String> res = new ArrayList<>();
        traceTree(charWrapperArrayList, new ArrayList<>(), res);
        return res;
    }

    private static void traceTree(List<CharWrapper> charWrappers, List<String> trace, List<String> result) {
        if (trace.size() == charWrappers.size()) {
            result.add(String.join("", trace));
            return;
        }
        for (CharWrapper charWrapper : charWrappers) {

            if (charWrapper.hasChoose) {
                continue;
            }

            charWrapper.hasChoose = true;
            trace.add(String.valueOf(charWrapper.c));
            traceTree(charWrappers, trace, result);
            charWrapper.hasChoose = false;
            trace.remove(String.valueOf(charWrapper.c));
        }
    }

    public static class CharWrapper {
        char c;
        boolean hasChoose;

        public CharWrapper(char c, boolean hasChoose) {
            this.c = c;
            this.hasChoose = hasChoose;
        }

    }

}
