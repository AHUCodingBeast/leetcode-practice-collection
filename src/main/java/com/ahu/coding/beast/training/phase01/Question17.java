package com.ahu.coding.beast.training.phase01;

import com.alibaba.fastjson2.JSON;

/**
 * @author jianzhang
 * 2025/02/10/下午4:33
 *
 * <a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/">原题链接</a>
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 */
public class Question17 {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(searchRange(new int[]{5,7,7,8,8,10},8)));
        System.out.println(JSON.toJSONString(searchRange(new int[]{1,2,2,2,4},2)));
        System.out.println(JSON.toJSONString(searchRange(new int[]{5,7,7,8,8,10},6)));
    }

    /**
     * 对于这道题，要求logN的复杂度 加之数组有序 首先想到的应该就是二分查找方法
     * <p>
     * 但是比如说数组是[1,2,2,2,4] 直接用二分查找可能只能找到2号索引的位置，不能直接用作这题的解
     * <p>
     * 因此找到2号索引的位置之后二分查找应该还要继续进行不能结束了,应该直接把这个元素排除掉，再接着二分
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        int index1 = -1;
        int index2 = -1;

        // 第一次二分 找第一个等于target的位置
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                index1 = mid;
                // 关键位置这里不能结束了，还得继续找
                high = mid - 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }


        // 第二次二分 找最后一个等于target的位置
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                index2 = mid;
                // 关键位置这里不能结束了，还得继续找
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }


        return new int[]{index1, index2};
    }

}
