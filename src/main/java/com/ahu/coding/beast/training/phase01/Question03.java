package com.ahu.coding.beast.training.phase01;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jianzhang
 * 2025/01/14/下午5:19
 * <p>
 * 最长的无重复字符的子串
 */
public class Question03 {
    public static void main(String[] args) {

        Question03 question03 = new Question03();
        System.out.println(question03.getLongestSubString("abcdefgg"));
        System.out.println(question03.getLongestSubString("bbbbbb"));
    }


    /**
     * 官方解法 例如 abbcdef 最长的无重复字符的子串是 bcdef
     * i 指向 a j指向第二个b的时候此时set里面已经存在b了
     * i则需要移动
     *
     * @param s 原始字符串
     * @return 最长无重复字符的子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
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
