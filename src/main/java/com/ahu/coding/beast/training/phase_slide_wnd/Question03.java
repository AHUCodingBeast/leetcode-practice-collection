package com.ahu.coding.beast.training.phase_slide_wnd;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jianzhang
 * 2025/01/14/下午5:19
 * <p>
 * 最长的无重复字符的子串
 * <p>
 * 例如 abcdefgg 最长无重复字符的子串为 abcdefg 应该输出7
 */
public class Question03 {
    public static void main(String[] args) {

        Question03 question03 = new Question03();
//        System.out.println(question03.getLongestSubString("abcdefgg"));
//        System.out.println(question03.getLongestSubString("bbbbbb"));

//        System.out.println(question03.getLongestSubStringBySlidingWindow("abcdefgg"));
//        System.out.println(question03.getLongestSubStringBySlidingWindow("bbbbbb"));

        System.out.println(question03.getLongestSubStringBySlidingWindowByTemplate("abcdefgg"));
        System.out.println(question03.getLongestSubStringBySlidingWindowByTemplate("bbbbbb"));
    }

    /**
     * 时间窗口类题型 模版类写法
     *
     * @param target 目标字符串
     * @return 最长无重复字符的子串长度
     */
    public int getLongestSubStringBySlidingWindowByTemplate(String target) {

        int left = 0, right = 0;
        int maxLen = 0;
        int len = 0;
        Set<Character> window = new HashSet<>();
        while (right < target.length()) {

            char rightChar = target.charAt(right);
            if (!window.contains(rightChar)) {
                window.add(rightChar);
                len++;
            } else {
                while (right - left >= 0 && window.contains(rightChar)) {
                    char leftChar = target.charAt(left);
                    if (window.contains(leftChar)) {
                        window.remove(leftChar);
                        len--;
                    }
                    left++;
                }
                window.add(rightChar);
                len++;
            }

            if (len > maxLen) {
                maxLen = len;
            }

            right++;
        }

        return maxLen;
    }


    /**
     * 滑动窗口思路：
     * 假设我们选择字符串中的第 k 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 rK
     * 这时候如果我们重新选择第k+1 个字符作为起始位置，k+1 -> rK 之间一定没有重复的字符，所以只需要考虑增大rK就行
     * <p>
     * 参考：<a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/">...</a>
     *
     * @param target 目标字符串
     * @return 最终的最长无重复字符的子串
     */
    public int getLongestSubStringBySlidingWindow(String target) {

        if (target == null) {
            return -1;
        }
        // 这个set就相当记录了窗口里面的所有元素
        Set<Character> set = new HashSet<>();
        int maxlength = -1;
        for (int i = 0; i < target.length(); i++) {
            set.add(target.charAt(i));
            int j = i + 1;
            while (j < target.length() && !set.contains(target.charAt(j))) {
                // 往窗口中加进去一个元素
                set.add(target.charAt(j));
                j++;
            }
            int len = j - i;
            if (len > maxlength) {
                maxlength = len;
            }
            // 换掉开头的元素，窗口滑动一下，把第一个元素给拿掉
            set.remove(target.charAt(i));
        }

        return maxlength;
    }

    /**
     * 个人解法 相当于暴力求解 但是会简化第二轮循环，也就是说如果从i->j 存在重复字符, j就不需要继续移动了
     *
     * @param target 目标字符串
     * @return 最长无重复字符的子串长度
     */
    public int getLongestSubString(String target) {
        if (StrUtil.isEmpty(target)) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < target.length(); i++) {
            for (int j = i + 1; j < target.length(); j++) {
                if (subStringHasRepeatChar(target, i, j)) {
                    break;
                } else {
                    if (max < (j - i)) {
                        max = j - i;
                    }
                }
            }
        }
        return max;
    }

    public boolean subStringHasRepeatChar(String str, int start, int end) {
        if (StrUtil.isEmpty(str)) {
            return false;
        }
        String target = str.substring(start, end);
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            if (map.get(target.charAt(i)) != null) {
                return true;
            }
            map.put(target.charAt(i), i);
        }
        return false;
    }
}
