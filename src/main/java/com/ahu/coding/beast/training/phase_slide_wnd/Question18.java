package com.ahu.coding.beast.training.phase_slide_wnd;

import com.ahu.coding.beast.tools.SortAlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianzhang
 * 2025/02/11/下午6:59
 * 滑动窗口练习
 */
public class Question18 {

    public static void main(String[] args) {
//        System.out.println(getMaxComposeData(new int[]{1, 1, 1, 1, 3}, 4));
//        System.out.println(getMaxComposeData(new int[]{1, 1, 1, 1, 3}, 5));
//        System.out.println(getMaxComposeData(new int[]{1, 1, 1, 1, 3}, 9));
        System.out.println(getMaxComposeData2(new int[]{1, 1, 1, 1, 3}, 5));

        int[] arr = {1, 2, 3, -3, 3};
        int k = 3;
        System.out.println(getMaxComposeData3(arr,k));
    }

    /**
     * 题目1：给定一个数组，和一个整数N  要求找到能组成整数N的最长组合长度
     * 给定nums={1,1,1,3,1} 组成 数字4  最长的组合长度为4  对应的组合为{1,1,1,1}
     */
    public static int getMaxComposeData(int[] nums, int target) {
        // 通过排序把不连续的问题转为连续问题
        int[] sortedNums = SortAlgorithmUtils.quickSort(nums);
        int left = 0, right = 0, maxLen = 0;
        int wnd = 0;
        while (right < nums.length) {
            int curRight = sortedNums[right];
            wnd += curRight;
            while (left < right && wnd >= target) {
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

    /**
     * 题目2：给定一个全是正数的数组，和一个整数N  要求找到能组成整数N的最长子数组长度
     * 给定nums={1,1,1,3,1} 组成 数字4  最长的组合长度为4  对应的组合为{1,3} 长度为2
     * <p>
     * 这题和上面这道题比起来就一个不同就是要求连续了, 要求连续了反而更好解决了，排序都不需要了
     */
    public static int getMaxComposeData2(int[] nums, int target) {

        int left = 0, right = 0, sum = 0, maxLen = 0;

        while (right < nums.length) {
            sum += nums[right];
            while (left <= right && sum >= target) {
                if (sum == target) {
                    int windowSize = right - left + 1;
                    if (windowSize > maxLen) {
                        maxLen = windowSize;
                    }
                }
                left++;
                sum -= nums[left];
            }
            right++;
        }
        return maxLen;
    }

    /**
     * 题目3：给定一个数组，值可以为正、负和0，请返回累加和为给定值k的最长子数组长度。
     * 有了负数之后就不能用滑动窗口了，因为收缩窗口的条件不行了 在上面的案例中我们发现sum的值比目标大的时候就收缩窗口 ，
     * 但是在有负数的情况下 收缩窗口可能会让sum变得更加的大 比如我们要移出窗口的数字是负数的时候 移除之后反而sum变得更加的大了
     * <p>
     * 怎么解决呢？我们需要用到 前缀和 + hash表的方法
     * <p>
     * 假定现在有数组我们计算出来每个前缀和的值有
     * prefixSum[i] = m 代表从0到i的和值,即 nums[0]+nums[1]+...+nums[i] = m;
     * 假定还存在 prefixSum[j] = m-k (j>i), 即 nums[0]+nums[1]+...+nums[i] +nums[i+1] + ... + nums[j] =  m-k;
     * 那么将两个式子相减就有  nums[i+1] + ... + nums[j]  = k
     * 你就找到了一个和为k的子数组，然后考察一下他的长度是不是最长的就行了
     */
    public static int getMaxComposeData3(int[] arr, int k) {
        // 初始化哈希表，用于记录前缀和及其首次出现的位置
        Map<Integer, Integer> prefixSumIndex = new HashMap<>();
        // 存入前缀和为 0 时的位置为 -1，方便处理从数组起始位置开始的子数组
        prefixSumIndex.put(0, -1);
        // 记录当前的前缀和
        int currentSum = 0;
        // 记录最长子数组的长度
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            // 计算当前位置的前缀和
            currentSum += arr[i];
            // 检查 prefixSum - k 是否在哈希表中
            if (prefixSumIndex.containsKey(currentSum - k)) {
                // 更新最长子数组的长度
                maxLength = Math.max(maxLength, i - prefixSumIndex.get(currentSum - k));
            }
            // 如果当前前缀和不在哈希表中，不断更新前缀和的结束位置下标
            if (!prefixSumIndex.containsKey(currentSum)) {
                prefixSumIndex.put(currentSum, i);
            }
        }
        return maxLength;
    }


}
