package com.ahu.coding.beast.training.phase01;

import java.util.*;

/**
 * @author jianzhang
 * 2025/02/10/上午10:39
 * {@link Question03}
 * <p>
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
        System.out.println(containArrangement2("ab", "eidbaooo"));
        System.out.println(containArrangement2("ab", "eidboaoo"));
        System.out.println(containArrangement3("ab", "eidbaooo"));
        System.out.println(containArrangement3("ab", "eidboaoo"));
    }

    /**
     * 时间窗口标准解法
     *
     * @param t 字符串1
     * @param s 字符串2
     * @return 结果
     */
    public static Boolean containArrangement3(String t, String s) {

        HashMap<Character, Integer> bench = str2HashMap(t);
        HashMap<Character, Integer> windowRecord = new HashMap<>();
        int left = 0, right = 0, validNum = 0;

        while (right < s.length()) {

            char curChar = s.charAt(right);
            int windowLen = right - left + 1;

            if (bench.containsKey(curChar)) {
                windowRecord.compute(curChar, (k, v) -> v == null ? 1 : v + 1);
                if (Objects.equals(windowRecord.get(curChar), bench.get(curChar))) {
                    validNum++;
                }
            }

            while (windowLen >= t.length()) {
                if (validNum == bench.size()) {
                    return true;
                }
                char leftChar = s.charAt(left);
                if (windowRecord.get(leftChar) != null) {
                    if (Objects.equals(windowRecord.get(leftChar), bench.get(leftChar))) {
                        validNum--;
                    }
                    windowRecord.put(leftChar, windowRecord.get(leftChar) - 1);
                }
                left++;
                windowLen = right - left + 1;
            }
            right++;
        }

        return false;
    }

    /**
     * 解法2：检查字符串2中长度为s1的所有子串，转为HashMap之后统计两个map是否相等
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 题解
     */
    public static Boolean containArrangement2(String s1, String s2) {

        if (s1 == null || s2 == null) {
            return false;
        }

        // 先考虑一个问题，假定两个字符串长度一样的情况下怎么判断一个字符串是不是另一个字符串的组合？
        // 其实比较简单就是 每种元素个数要一样，也不能有多余的元素,所以直接变为两个hashMap之间的比较
        HashMap<Character, Integer> base = str2HashMap(s1);
        for (int i = 0; i < s2.length(); i++) {
            int j = i + s1.length();
            if (j > s2.length()) {
                break;
            }
            String sub = s2.substring(i, j);
            if (compareHashMap(base, str2HashMap(sub))) {
                return true;
            }
        }
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

    public static HashMap<Character, Integer> str2HashMap(String str) {
        HashMap<Character, Integer> res = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            res.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return res;
    }

    public static boolean compareHashMap(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {

        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            Character k = entry.getKey();
            Integer v = entry.getValue();
            if (map2.get(k) == null || map2.get(k) != v) {
                return false;
            }
        }
        return true;
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
