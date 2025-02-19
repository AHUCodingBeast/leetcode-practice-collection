package com.ahu.coding.beast.training.phase_tree;

import java.util.ArrayList;
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

}
