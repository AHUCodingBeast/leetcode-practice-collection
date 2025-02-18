package com.ahu.coding.beast.training.phase_dp;

import com.ahu.coding.beast.tools.SortAlgorithmUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jianzhang
 * 2025/01/16/下午3:42
 * <p>
 * 换零钱（<a href="https://leetcode.cn/problems/coin-change/description/">...</a>）
 * 假定现在有 1元 5元 10元 20元 50元 100元 6种面值的人民币若干，现在给定一个正整数 n 请你给最少个数的钱币组合
 * 例如给定95，可以使用50 + 20+ 20 +5 的组合 使用的纸币最少（4个）
 * {@link Question10}
 */
public class Question09 {

    public static void main(String[] args) {

//        System.out.println(findShortestCombine(6249, new int[]{186, 419, 83, 408}));
//        System.out.println(findShortestCombine(191, new int[]{100, 50, 20, 10, 5}));
//        System.out.println(findShortestCombine(191, new int[]{100, 50, 20, 10, 5, 1}));

        System.out.println(findShortestCombineLength(6, new int[]{2, 5, 10}));
        System.out.println(findShortestCombine(6, new int[]{2, 5, 10}));
        System.out.println(findShortestCombine(6249, new int[]{186, 419, 83, 408}));


    }


    /**
     * 数学定义 假定money[i] 是第i种面值  count[i] 代表第i种面值的选用数量，则问题就是求解 target = SUM[(i:1->n) {count[i] * money[i]}]
     * <p>
     * 定义F(i)为组成金额i所需最少的硬币数量,现在求解F(i) ，假定我们的钱币名额有三种分别为 [2,5,10]
     * 那么求解 F(i) = min(F(i-2),F(i-5),F(i-10))+1
     * 例如我们求解 F(4) =  min(F(2),F(-1),F(-6))+1 = 2
     * 显然存在这样一种情况就是 已知的面额无法组装成目标值，例如我们要求解 F(3)
     * F(3) = min(F(1),F(-2),F(-7))+1  这个时候F(1),F(-2),F(-7) 都是无解  所以部分无解的子问题需要设置为一个尽可能大的值，例如设置为target+1
     * <p>
     * 符合DP问题的最优子结构
     */
    public static Integer findShortestCombineLength(int target, int[] money) {
        Map<Integer, Map<Integer, Long>> map = new HashMap<>();
        int[] dp = new int[target + 1];
        Arrays.fill(dp, target + 1);

        // 关键点组成0元的最少货币数是0 一个都不选
        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int m : money) {
                if (i - m >= 0) {
                    if (dp[i] > dp[i - m] + 1) {
                        dp[i] = dp[i - m] + 1;
                    }
                }
            }
        }
        if (dp[target] >= target + 1) {
            return -1;
        }
        return dp[target];
    }


    /**
     * 在 {@link Question09#findShortestCombineLength(int, int[])} 中只是得出来了最少货币张数，实际上的货币组合并没有输出，这里更进一步输出最终的组合结果
     */
    public static Map<Integer, Integer> findShortestCombine(int target, int[] money) {
        Map<Integer, Integer> res = new HashMap<>();
        for (int m : money) {
            res.put(m, 0);
        }

        int[] dp = new int[target + 1];
        // dp[i] = min{dp[i-c1],dp[i-c2]..} + 1
        Arrays.fill(dp, target + 1);
        dp[0] = 0;

        // key:面额  value:最后一次选择的面值
        Map<Integer, Integer> record = new HashMap<>();

        for (int i = 0; i < dp.length; i++) {
            int lastChoose = -1;
            for (int m : money) {
                if (i - m >= 0) {
                    if (dp[i] > dp[i - m] + 1) {
                        dp[i] = dp[i - m] + 1;
                        // 不断更新最后一次选择的面额数值
                        lastChoose = m;
                    }
                }
            }
            if (lastChoose != -1) {
                record.put(i, lastChoose);
            }
        }

        if (dp[target] >= target + 1) {
            return null;
        }

        // 回溯记录表 还原最优选择
        int temp = target;
        while (record.get(temp) != null) {
            res.put(record.get(temp), res.get(record.get(temp)) + 1);
            temp -= record.get(temp);
        }

        return res;

    }


    /**
     * 如果硬币的面值固定就是  1元 5元 10元 20元 50元 100元 6种面值  这时候可以考虑使用贪心算法，因为面值之间存在倍数关系
     * <p>
     * 但是如果面值之间毫无联系，一直使用贪心算法，可能会导致最终无解，但是实际上未必无解
     * 比如 1, 3, 4 组 6 元的这个case  按照贪心会选择 1个4元 2个1元 最终三张纸币  但是实际上选 两张三元的才是最优解
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
