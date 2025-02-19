package com.ahu.coding.beast.training.phase_tree;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianzhang
 * 2025/01/26/上午11:02
 * <p>
 * 题目 ：给定一个没有重复数字的序列，请你给出所有的全排列
 * <p>
 * 这道题我给了两种写法本质是一个思想
 * - 第一种是定义一个Node对象来包装num 方便记录目标数字有没有被选择
 * - 第二种则是用一个used数组来记录目标数字有没有被选择过
 */
public class Question11 {

    public static void main(String[] args) {
        List<List<Integer>> allArrangement = getAllArrangement01(List.of(1, 2, 3));
        System.out.println(allArrangement);

        List<List<Integer>> allArrangement2 = getAllArrangement02(List.of(1, 2, 3));
        System.out.println(allArrangement2);
    }

    public static List<List<Integer>> getAllArrangement02(List<Integer> nums) {
        boolean[] used = new boolean[nums.size()];
        List<List<Integer>> result = new ArrayList<>();
        trace(nums, used, new ArrayList<>(), result);
        return result;
    }

    public static List<List<Integer>> getAllArrangement01(List<Integer> nums) {
        if (CollectionUtil.isEmpty(nums)) {
            return null;
        }
        List<Node> nodeList = nums.stream().map(e -> new Node(e, false)).toList();
        List<Node> trace = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        getAllArrangementSub(nodeList, trace, res);
        return res;
    }

    static void getAllArrangementSub(List<Node> nodeList, List<Node> trace, List<List<Integer>> res) {

        if (trace.size() == nodeList.size()) {
            res.add(trace.stream().map(e -> e.num).toList());
            return;
        }

        for (Node node : nodeList) {
            if (node.hasChoose) {
                continue;
            }
            // 选当前元素
            node.hasChoose = true;
            trace.add(node);

            getAllArrangementSub(nodeList, trace, res);

            // 撤销当前选择
            node.hasChoose = false;
            trace.remove(node);
        }

    }

    public static void trace(List<Integer> nums, boolean[] used, List<Integer> traceList, List<List<Integer>> res) {

        // 路径的长度等于nums的长度的时候代表存在一个可行解了
        if (traceList.size() == nums.size()) {
            res.add(new ArrayList<>(traceList));
        }

        for (int i = 0; i < nums.size(); i++) {
            if (used[i]) {
                continue;
            }
            // 做选择
            used[i] = true;
            traceList.addLast(nums.get(i));

            // 继续遍历树
            trace(nums, used, traceList, res);

            // 撤销选择
            traceList.removeLast();
            used[i] = false;
        }
    }

    @AllArgsConstructor
    static class Node {
        Integer num;
        boolean hasChoose;
    }


}
