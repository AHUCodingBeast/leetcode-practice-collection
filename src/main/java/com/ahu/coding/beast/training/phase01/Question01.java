package com.ahu.coding.beast.training.phase01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author jianzhang
 * 2025/01/14/下午4:46
 * <p>
 * 关联例题 {@link Question28}   {@link Question01}
 * <p>
 * <p>
 * 原题目：
 * 给定一个 有序 数组，还给定一个两数之和
 * [2,7,11,15] target = 9
 * 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 * <p>
 * <p>
 * 这里有三种思路
 * （1）二分查找法：首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。利用数组的有序性质，可以通过二分查找的方法寻找第二个数 [只要是有序的数组就可以考虑使用二分查找提升搜索性能]
 * （2）Hash查找法：首先将所有的数字加到hashMap中，然后依次固定一个数字，然后去hashMap中找另一个数字，需要注意的是数字不能重复
 * （3）双指针法（最经典）：
 * 初始时两个指针分别指向第一个元素位置和最后一个元素的位置。
 * 每次计算两个指针指向的两个元素之和，并和目标值比较。
 * 如果两个元素之和等于目标值，则发现了唯一解。
 * 如果两个元素之和小于目标值，则将左侧指针右移一位。
 * 如果两个元素之和大于目标值，则将右侧指针左移一位。移动指针之后，重复上述操作，直到找到答案。
 */
public class Question01 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(twoSum3(new int[]{2, 7, 11, 15}, 9)));

        int[] nums = new int[]{-1, 2, 2, 7, 10, 11, 15};
        int target = 9;
        System.out.println(getTwoSumPairList(nums, target));
    }

    private static int[] twoSum1(int[] nums, int target) {
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

    public static int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {

            int sub = target - numbers[i];
            int low = i + 1;
            int high = numbers.length - 1;

            // 二分查找，这里只需要在i位置往后找就行了
            while (low <= high) {
                int mid = (low + high) / 2;
                if (numbers[mid] > sub) {
                    high = mid - 1;
                } else if (numbers[mid] < sub) {
                    low = mid + 1;
                } else {
                    return new int[]{i, mid};
                }
            }
        }
        return null;
    }


    /**
     * 双指针法
     *
     * @param numbers 原始数组
     * @param target  目标值
     */
    public static int[] twoSum3(int[] numbers, int target) {
        int leftIndex = 0;
        int rightIndex = numbers.length - 1;

        while (leftIndex < rightIndex) {
            if (numbers[leftIndex] + numbers[rightIndex] > target) {
                rightIndex--;
            } else if (numbers[leftIndex] + numbers[rightIndex] < target) {
                leftIndex++;
            } else {
                return new int[]{leftIndex, rightIndex};
            }
        }
        return null;
    }


    /**
     * 变种题目练习：
     * 给定一个 有序 数组（但是可能存在重复的数字），还给定一个两数之和 target 请你求解出所有能组成和target的组合，要求输出的结果不能存在重复的组合
     * 举例：
     * [2,2,7,11,15] target = 9  你的输出结果应该为[2,7]
     */
    public static List<List<Integer>> getTwoSumPairList(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0;
        int right = len - 1;
        List<List<Integer>> result = new ArrayList<>();
        while (left <= right) {

            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                result.add(List.of(numbers[left], numbers[right]));
                while (left + 1 < len && numbers[left] == numbers[left + 1]) {
                    left++;
                }
                while (right - 1 >= 0 && numbers[right] == numbers[right - 1]) {
                    right--;
                }
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }

        }
        return result;
    }


}
