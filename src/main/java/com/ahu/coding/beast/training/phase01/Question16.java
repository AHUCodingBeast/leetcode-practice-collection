package com.ahu.coding.beast.training.phase01;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author jianzhang
 * 2025/02/10/下午4:30
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * <p>
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
public class Question16 {
    public static void main(String[] args) {

        System.out.println(getSubString("ADOBECODEBANC", "ABC"));
        System.out.println(getSubString("a", "aa"));

    }

    public static String getSubString(String s, String t) {

        HashMap<Character, Integer> base = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            base.compute(t.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }

        int right = 0, left = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int successMatchCount = 0;

        String minSubstring = s;

        while (right < s.length()) {

            char c = s.charAt(right);

            if (base.containsKey(c)) {
                window.compute(c, (k, v) -> v == null ? 1 : v + 1);
                if (Objects.equals(window.get(c), base.get(c))) {
                    // 窗口中有这个元素并且数目还相同，说明成功匹配上一对
                    successMatchCount++;
                }
            }

            // 窗口已经符合题意找到了一个可行解，考虑收缩窗口
            while (right >= left && successMatchCount >= base.size()) {

                String curSolution = s.substring(left, right + 1);
                if (curSolution.length() < minSubstring.length()) {
                    minSubstring = curSolution;
                }

                // 移除左指针元素,需要考虑修改窗口属性的值，对应的就是下面的这两个if
                char leftC = s.charAt(left);

                if (base.containsKey(leftC) && Objects.equals(base.get(leftC), window.get(leftC))) {
                    successMatchCount--;
                }

                if (window.get(leftC) != null) {
                    window.put(leftC, window.get(leftC) - 1);
                }

                left++;
            }

            right++;

        }

        if (minSubstring.equals(s)) {
            return "";
        }

        return minSubstring;
    }
}
