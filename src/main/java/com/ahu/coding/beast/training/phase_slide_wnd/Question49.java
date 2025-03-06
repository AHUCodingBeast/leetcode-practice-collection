package com.ahu.coding.beast.training.phase_slide_wnd;

/**
 * @author jianzhang
 * 2025/03/06/下午5:31
 * <p>
 * 给定一个有n个 有序 正整数的数组arr 和一个整数m ,求选择数组arr 中部分数字和为m的最短连续子数组
 */
public class Question49 {


    public static void main(String[] args) {

        System.out.println(getShortest(new int[]{1, 4, 2, 1, 1, 1, 1, 2, 2}, 4));

    }

    public static int getShortest(int[] nums, int sum) {
        int left = 0, right = 0;
        int n = nums.length;
        int minLen = nums.length + 1;

        int windowSum = 0;
        while (right < n) {
            windowSum += nums[right];
            while (left <= right && windowSum >= sum) {
                if (windowSum == sum) {
                    int len = right - left + 1;
                    if (len < minLen) {
                        minLen = len;
                    }
                }
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return minLen == nums.length + 1 ? -1 : minLen;
    }
}
