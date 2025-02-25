package com.ahu.coding.beast.training.phase_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author jianzhang
 * 2025/02/18/下午8:14
 * <p>
 * 题目给你输入一个无重复元素的数组 nums，其中每个元素最多使用一次，请你返回 nums 的所有子集。
 * 比如输入 nums = [1,2,3]，算法应该返回如下子集：
 * [ [],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3] ]
 */
public class Question30 {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));

        System.out.println(doTraceByStack(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doTrace(nums, 0, new ArrayList<>(), result);
        return result;
    }

    static void doTrace(int[] nums, int startIndex, List<Integer> trace, List<List<Integer>> result) {
        result.add(new ArrayList<>(trace));
        for (int i = startIndex; i < nums.length; i++) {
            trace.addLast(nums[i]);
            // 只能追加位于目标元素之后的元素
            doTrace(nums, i + 1, trace, result);
            trace.removeLast();
        }
    }


    /**
     * 递归的实现思路也可以转用栈来实现 但是感觉这样去写可读性会比较差 可以做下了解
     */
    static List<List<Integer>> doTraceByStack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 初始化栈，栈中存放元组 (startIndex, 当前路径)
        Deque<Pair<Integer, List<Integer>>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(0, new ArrayList<>()));

        while (!stack.isEmpty()) {
            Pair<Integer, List<Integer>> pair = stack.pop();
            // 当前起始下标
            int startIndex = pair.getKey();
            // 当前已有路径
            List<Integer> trace = pair.getValue();
            // 将当前路径添加到结果集中
            result.add(new ArrayList<>(trace));

            for (int i = startIndex; i < nums.length; i++) {
                List<Integer> newTrace = new ArrayList<>(trace);
                newTrace.add(nums[i]);

                // 将新的路径和下一个起始索引推入栈中
                stack.push(new Pair<>(i + 1, newTrace));
            }
        }

        return result;
    }


    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
