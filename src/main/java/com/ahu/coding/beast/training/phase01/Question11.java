package com.ahu.coding.beast.training.phase01;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianzhang
 * 2025/01/26/上午11:02
 * <p>
 * 题目 ： 给定一个没有重复数字的序列，请你给出所有的全排列
 */
public class Question11 {

    public static void main(String[] args) {
        List<List<Integer>> allArrangement = getAllArrangement(List.of(1, 2, 3));
        System.out.println(allArrangement);
    }

    public static List<List<Integer>> getAllArrangement(List<Integer> nums) {

        if (CollectionUtil.isEmpty(nums)) {
            return null;
        }
        List<Node> nodeList = nums.stream().map(e -> new Node(e, false)).toList();
        List<Integer> arrange = new ArrayList<>();

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


    @AllArgsConstructor
    static class Node {
        Integer num;
        boolean hasChoose;
    }

}
