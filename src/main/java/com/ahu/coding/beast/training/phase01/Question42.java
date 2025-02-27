package com.ahu.coding.beast.training.phase01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianzhang
 * 2025/02/27/上午10:14
 * <p>
 * 输入n个数字，将这n个数字分成两组，要求能被5整除的数字和能被3整除的数字不能分在同一组中，其他数字分组任意，另一个要求就是要求分成的两个数组其和要相同。
 * 能满足以上条件，返回true；不满足时返回false。
 * 如：输入 1 -5 5 1
 * 可以分为1和-5,5,1；
 */
public class Question42 {


    public static void main(String[] args) {


        System.out.println(hasDivide01(new int[]{1, -5, 10, 1}));
        System.out.println(hasDivide01(new int[]{1, -5, 5, 1}));


    }

    /**
     * 先说下这题的一个思路
     * 思路1：
     * 首先我们需要把原始数组分为三部分一部分是能够被5整除的 假定其和为 A 一部分是能被3整除的 假定其和为 B ，最后一部分和为C 并将最后一部分的数字单独收编在一个数组arr里面
     * 显然 我们需要求解一种划分使得  A + x1 = B + x2 , 且 x1+x2=C 简单合并下, 问题就变为问你数组 arr中是否存在一种组合使得 (B+C-A)/2 于是这个问题就可以直接套用之前的组合问题通用解法了
     * 虽然这样解是可以的但是需要考虑的问题比较多首先 需要判断(B+C-A)/2 是不是整数  此外还需要考虑数组中是否有重复数字的问题，如果有重复数字还需要先排序。有点麻烦
     * <p>
     * 思路2：
     * 还是和思路1 一样先把数字分为三部分，一部分是能够被5整除的 假定其和为 A 一部分是能被3整除的 假定其和为 B 将最后一部分的数字单独收编在一个数组arr里面
     * 接下来考察arr里面的每一个元素，对于每个元素而言有两种选择要么加入A要么加入B，于是递归的感觉就来了
     */
    public static boolean hasDivide01(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        int sum5 = 0;
        int sum3 = 0;
        for (int num : nums) {
            if (num % 5 == 0) {
                sum5 += num;
            } else if (num % 3 == 0) {
                sum3 += num;
            } else {
                arr.add(num);
            }
        }
        return trace(sum3, sum5, arr, 0);
    }


    private static boolean trace(int sum1, int sum2, List<Integer> arr, int index) {

        if (index == arr.size() && sum1 == sum2) {
            return true;
        }

        if (index == arr.size()) {
            return false;
        }

        // 每个元素要么加到sum1上面要么加到sum2上面 就是一个二叉树的DFS遍历
        return trace(sum1 + arr.get(index), sum2, arr, index + 1) ||
                trace(sum1, sum2 + arr.get(index), arr, index + 1);

    }


    public static boolean hasDivide02(int nums[]) {
        int sum5 = 0;
        int sum3 = 0;
        int sumNot3Or5 = 0;
        List<Integer> not3or5 = new ArrayList<>();
        for (int num : nums) {
            if (num % 5 == 0) {
                sum5 += num;
            } else if (num % 3 == 0) {
                sum3 += num;
            } else {
                not3or5.add(num);
                sumNot3Or5 += num;
            }
        }
        // 需要判断target是不是整数，如果有重复数字还要先排序
        int target = (sumNot3Or5 + sum3 - sum5) / 2;
        return trace(nums, target, 0, new ArrayList<>());
    }


    public static boolean trace(int[] nums, int target, int start, List<Integer> trace) {

        int sum = 0;
        for (Integer num : trace) {
            sum += num;
        }
        if (sum == target) {
            return true;
        }

        for (int i = start; i < nums.length; i++) {
            trace.addLast(nums[i]);
            boolean result = trace(nums, target, start + 1, trace);
            if (result) {
                return true;
            }
            trace.removeLast();
        }
        return false;

    }


}
