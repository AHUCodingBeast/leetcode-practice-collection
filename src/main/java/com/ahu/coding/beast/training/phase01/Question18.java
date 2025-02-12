package com.ahu.coding.beast.training.phase01;

import com.ahu.coding.beast.tools.SortAlgorithmUtils;

/**
 * @author jianzhang
 * 2025/02/11/下午6:59
 * <p>
 * 题目：给定一个数组，和一个整数N  要求找到能组成整数N的最长组合长度
 * <p>
 * 例如 给定nums={1,1,1,3,1} 组成 数字4  最长的组合长度为4  对应的组合为{1,1,1,1}
 */
public class Question18 {

    public static void main(String[] args) {
        System.out.println(getMaxComposeData(new int[]{1,1,1,1,3},4));
        System.out.println(getMaxComposeData(new int[]{1,1,1,1,3},5));
        System.out.println(getMaxComposeData(new int[]{1,1,1,1,3},9));
    }

    /**
     * 老方法还是使用滑动窗口法
     * @param nums
     * @param target
     * @return
     */
    public static int getMaxComposeData(int[] nums, int target) {

        // 通过排序把不连续的问题转为连续问题
        int[] sortedNums = SortAlgorithmUtils.quickSort(nums);

        int left = 0, right = 0, maxLen = 0;
        int wnd = 0;
        while (right < nums.length) {
            int curRight = sortedNums[right];
            wnd += curRight;
            while (wnd >= target) {
                if (wnd == target) {
                    int len = right - left + 1;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
                wnd -= sortedNums[left];
                left++;
            }
            right++;
        }
        return maxLen;
    }

}
