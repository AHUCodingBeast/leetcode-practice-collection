package com.ahu.coding.beast.training.phase_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jianzhang
 * 2025/02/18/下午5:42
 * <p>
 * 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 * <a href="https://leetcode.cn/problems/4sjJUc/description/">...</a>
 * <p>
 * 示例 1：
 * <p>
 * 输入：candidates = [10,1,2,7,6,1,5], target = 8
 * 输出：
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class Question32 {

    public static void main(String[] args) {
        System.out.println(getAllCombineForTarget(List.of(10, 1, 2, 7, 6, 1, 5), 8));
    }

    public static List<List<Integer>> getAllCombineForTarget(List<Integer> numberList, Integer target) {
        List<List<Integer>> result = new ArrayList<>();
        // 注意这里额外增加了一个排序操作, 之所以要排序就是为了避免原始数组里面给了重复的数字  导致最终输出的结果里面存在重复
        List<Integer> sortedNumberList = numberList.stream().sorted().collect(Collectors.toList());
        trace(sortedNumberList, new ArrayList<>(), 0, target, result);
        return result;
    }


    /**
     * 直接套用之前的子集模板可以发现最终的输出结果是 [[1, 2, 5], [1, 7], [1, 6, 1], [2, 6], [2, 1, 5], [7, 1]]
     * 存在重复的组合  比如[1,7] 和 [1, 2, 5] 所以这种存在重复数字的组合问题，就需要考虑“剪枝”
     */
    public static void trace(List<Integer> numberList, List<Integer> path, int start, Integer target, List<List<Integer>> result) {

        int sum = 0;
        for (Integer v : path) {
            sum += v;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 如果和已经超过目标值了也可以提前结束，不需要完成所有树的节点遍历
        if (sum > target) {
            return;
        }

        for (int i = start; i < numberList.size(); i++) {
            // 关键点： 如果第i位和第i-1 位的数值是一样的 i-1 不继续往下发散数枝 你可以按照readMe中的介绍自己画一棵树就能明白为啥需要这样剪枝了
            if (i > start && Objects.equals(numberList.get(i), numberList.get(i - 1))) {
                continue;
            }
            path.addLast(numberList.get(i));
            trace(numberList, path, i + 1, target, result);
            path.removeLast();
        }

    }


}
