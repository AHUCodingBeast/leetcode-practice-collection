package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/27/下午3:56
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 示例 1：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * <p>
 * 示例 2：
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class Question14 {

    public static void main(String[] args) {
        char[] s = {'H','a','n','B'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c+"  ");
        }
    }

    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char t = s[right];
            s[right] = s[left];
            s[left] = t;
            left++;
            right--;
        }
    }

}
