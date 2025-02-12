package com.ahu.coding.beast.training.phase01;

import com.alibaba.fastjson2.JSON;

import java.util.*;

/**
 * @author jianzhang
 * 2025/02/12/上午10:21
 * 查找无序数组n中的最小的k个值，要求时间复杂度越低越好。
 */
public class Question21 {
    public static void main(String[] args) {

        int nums[] = {-1, 2, 1, 5, 7, -10, -9};
        System.out.println(minN(nums, 3));

        System.out.println(JSON.toJSONString(smallestKElements(nums, 3)));

    }

    /**
     * 方法1：借助堆结构，最为简单，但是需要依赖库函数
     */
    public static List<Integer> minN(int[] nums, int n) {
        if (nums == null || n > nums.length) {
            return null;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (int num : nums) {
            heap.add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            result.add(heap.poll());
        }

        return result;
    }

    /**
     * 方法2：借助快速排序
     */
    public static int[] smallestKElements(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        quickSelect(nums, 0, nums.length - 1, k - 1);
        int[] result = Arrays.copyOfRange(nums, 0, k);
        Arrays.sort(result);
        return result;
    }

    private static void quickSelect(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == k) {
            return;
        } else if (pivotIndex < k) {
            quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        swap(nums, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
