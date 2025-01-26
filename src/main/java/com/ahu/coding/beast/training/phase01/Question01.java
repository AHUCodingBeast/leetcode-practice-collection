package com.ahu.coding.beast.training.phase01;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author jianzhang
 * 2025/01/14/下午4:46
 * 原题目：
 * 两数之和
 * [2,7,11,15] target = 9
 * 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 */
public class Question01 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int add1 = nums[i];
            int add2 = target - add1;
            // 数字不能重复使用
            if (map.get(add2) != null && map.get(add2) != i) {
                return new int[]{i, map.get(add2)};
            }
        }
        return null;
    }

}
