package com.ahu.coding.beast.training.phase_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jianzhang
 * 2025/02/19/上午10:35
 * <p>
 * {@link Question11}
 * <p>
 * 给定一个可能包含重复元素的数组，请你给出全排列
 * 例如给定数组[1,2,2] 你需要返回 [ [1,2,2],[2,1,2],[2,2,1] ]
 * <p>
 * 分析：这道题目如果直接套用 {@link Question11} 的解法，最终结果不太对，主要是会出现重复结果，因为这里给定的原始数组存在重复
 */
public class Question33 {

    public static void main(String[] args) {

        System.out.println(getAllArrangementPro(List.of(1,2,2)));

    }

    /**
     * 当出现重复元素时，比如输入 nums = [1,2,2',2'']，2'
     * 只有在 2 已经被使用的情况下才会被选择，同理，2'' 只有在 2' 已经被使用的情况下才会被选择，这就保证了相同元素在排列中的相对位置保证固定。
     * 这样才不会出现 [2',2'',2,1] 和 [2',2,2'',1] 这样的重复结果
     */
    public static List<List<Integer>> getAllArrangementPro(List<Integer> nums) {
        List<Integer> sortedNums = nums.stream().sorted().toList();
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.size()];
        traceTree(sortedNums, result, used, new ArrayList<>());
        return result;
    }


    static void traceTree(List<Integer> nums, List<List<Integer>> result, boolean[] used, List<Integer> trace) {

        if (trace.size() == nums.size()) {
            result.add(new ArrayList<>(trace));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {

            if (used[i]) {
                continue;
            }

            if (i > 0 && Objects.equals(nums.get(i), nums.get(i - 1)) && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            trace.addLast(nums.get(i));

            traceTree(nums, result, used, trace);

            used[i] = false;
            trace.removeLast();

        }


    }

}
