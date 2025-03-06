package com.ahu.coding.beast.training.phase_slide_wnd;


import com.ahu.coding.beast.tools.ArrayPrinter;

import java.util.*;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 */
public class Question26 {

    public static void main(String[] args) {

        ArrayPrinter.print(getWindowMax(3, new int[]{2, 3, 4, 2, 6, 2, 5, 1}));

    }


    /**
     * 解法1：就是滑动窗口类题目的标准解法，套模板就行， 但是leetcode上无法通过大数据量时间测试
     */
    public static int[] getWindowMax(int windowSize, int[] nums) {

        int left = 0, right = 0;
        int len = nums.length;
        PriorityQueue<Integer> window = new PriorityQueue<>(Comparator.reverseOrder());
        List<Integer> result = new ArrayList<>();
        while (right < len) {
            window.add(nums[right]);
            while (left <= right && window.size() >= windowSize) {
                if (windowSize == window.size()) {
                    result.add(window.peek());
                }
                window.remove(nums[left]);
                left++;
            }
            right++;
        }

        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }


    /**
     * 解法2： 使用了一个单调队列的数据结构 可以O(1)的时间复杂度获取最大值
     */
    public int[] getWindowMaxWithMonoQueue(int[] nums, int sizeWindow) {
        List<Integer> record = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            if (deque.isEmpty()) {
                deque.addLast(i);
            } else {
                // 单调队列的典型写法 进队的元素需要把比他小的都给弹出去
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
            }

            // 得到当前滑动窗口的第一个元素的索引下标
            int beginIndex = i - sizeWindow + 1;
            if (beginIndex >= 0) {
                // 把队列头部已经不在窗口下标的元素给移除掉
                if (deque.peekFirst() < beginIndex) {
                    deque.pollFirst();
                }
                // 单调队列的头部始终都是最大值的下标
                record.add(nums[deque.peekFirst()]);
            }
        }

        int[] res = new int[record.size()];
        for (int i = 0; i < record.size(); i++) {
            res[i] = record.get(i);
        }
        return res;
    }


}
