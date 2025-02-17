package com.ahu.coding.beast.training.phase01;

import com.ahu.coding.beast.tools.SortAlgorithmUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jianzhang
 * 2025/01/16/下午3:42
 * <p>
 * 换零钱（<a href="https://leetcode.cn/problems/coin-change/description/">...</a>）
 * 假定现在有 1元 5元 10元 20元 50元 100元 6种面值的人民币若干，现在给定一个正整数 n 请你给最少个数的钱币组合
 * 例如给定95，可以使用50 + 20+ 20 +5 的组合 使用的纸币最少（4个）
 * <p>
 * 这题是个典型的“贪心算法” 我们需要尽可能的用大面值的钱去消耗目标数值
 * <p>
 * 但是一旦规定了钱币的数量，这题摇身一变就变成动态规划的题目了
 * {@link Question10}
 */
public class Question09 {

    public static void main(String[] args) {

        System.out.println(findShortestCombine(6249, new int[]{186, 419, 83, 408}));
//        System.out.println(findShortestCombine(191, new int[]{100, 50, 20, 10, 5}));
//        System.out.println(findShortestCombine(191, new int[]{100, 50, 20, 10, 5, 1}));

    }

    public static Map<Integer, Long> findShortestCombine(int target, int[] money) {

        return null;
    }



    /**
     * 这个解法是个错误解法
     * 这里的贪心思路不适合这道题的解
     * 原因是面值最大的纸币如果选的过多，可能会导致最终无解，但是实际上未必无解比如 186, 419, 83, 408 组 6249的这个case
     */
    public static Map<Integer, Long> findShortestCombineWrong(int target, int[] money) {
        if (target <= 0) {
            return null;
        }

        // 排序 从大到小排序
        money = sortArrayFromLargeToSmall(money);

        List<Integer> resultList = new ArrayList<>();
        for (int j : money) {
            while (target >= j) {
                resultList.add(j);
                target = target - j;
            }
        }
        // 考虑下不能组成目标金额的情况
        if (target > 0) {
            return null;
        }

        return resultList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

    }

    private static int[] sortArrayFromLargeToSmall(int[] nums) {
        return SortAlgorithmUtils.sortFromLarge2Small(nums, SortAlgorithmUtils::bubblingSort);
    }



}
