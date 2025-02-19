package com.ahu.coding.beast.training.phase_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author jianzhang
 * 2025/02/18/下午8:17
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 比如 combine(3, 2) 的返回值应该是：[ [1,2],[1,3],[2,3] ]
 */
public class Question31 {
    public static void main(String[] args) {
        System.out.println(combine(3,2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<Integer> nums = IntStream.range(1, n + 1).boxed().toList();
        List<List<Integer>> result = new ArrayList<>();
        trace(nums, new ArrayList<>(), 0, result, k);
        return result;
    }

    public static void trace(List<Integer> nums, List<Integer> path, int start, List<List<Integer>> result, int k) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
        }

        for (int i = start; i < nums.size(); i++) {
            path.addLast(nums.get(i));
            trace(nums, path, i + 1, result, k);
            path.removeLast();
        }
    }


}
