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

        System.out.println(findShortestCombine(95, new int[]{100, 10, 20, 50, 5}));
        System.out.println(findShortestCombine(191, new int[]{100, 50, 20, 10, 5}));
        System.out.println(findShortestCombine(191, new int[]{100, 50, 20, 10, 5, 1}));

    }


    public static Map<Integer, Long> findShortestCombine(int target, int[] money) {
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
