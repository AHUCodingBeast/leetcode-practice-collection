package com.ahu.coding.beast.training.phase01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author jianzhang
 * 2025/02/12/上午10:21
 * 查找无序数组n中的最小的k个值，要求时间复杂度越低越好。
 */
public class Question21 {
    public static void main(String[] args) {

        int nums[] = {-1, 2, 1, 5, 7, -10, -9};
        System.out.println(minN(nums, 3));

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


}
