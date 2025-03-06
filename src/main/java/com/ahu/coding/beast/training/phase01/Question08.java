package com.ahu.coding.beast.training.phase01;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/01/14/下午5:19
 * <p>
 * <p>
 * 直方图系列
 * <p>
 * 问题1：问你直方图中最大的矩形面积：有一个直方图，用一个整数数组表示，其中每列的宽度为1，求所给直方图包含的最大矩形面积。比如，对于直方图[2,7,9,4], 它所包含的最大矩形的面积为14(即[7,9]包含的7x2的矩形)。
 * <p>
 * 问题2：问你直方图能够承载多少水：比如{3,1,2,4};可以装的水量就是3。给定一个数组，求其对应的直方图能装多少水
 * <p>
 * 这两个问题，一个是单调栈问题，一个是双指针问题
 */
public class Question08 {
    public static void main(String[] args) {

        // int[] nums = new int[]{10, 8, 2, 3, 11, 5, 1};

        //   int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        int[] nums2 = new int[]{2, 7, 9, 4, 1};
        System.out.println(getMaxRecArea(nums2));

        int[] nums3 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapWater(nums3));

    }


    /**
     * 暴力解法：
     * 问题1 问你直方图的最大矩形面积，我们去考虑每一个柱子，这个柱子能和相邻的比它高的柱子组成矩形
     * 也就是说对于每个柱子 我需要考虑这个柱子往左边能扩展多长 比如 left 长度 然后考虑这个柱子能往右边拓展多长 比如right 长度
     * 那么最终构成的矩形面积就是 S = (left+right+1) * nums[i]
     * 依次计算每个柱子的最大面积 优中选优，就是最终的最大面积
     * <p>
     * <p>
     * 所谓单调栈就是在暴力解法的基础上解决如何快速找到左边第一个比当前柱子小的柱子以及右边第一个比当前柱子小的柱子
     * <p>
     * 这是典型的单调栈场景。我们需要构造一个从栈顶到栈底 从大到小的序列，这样一个新元素入栈的时候 需要把比他大的都弹出来，最后的栈顶元素就是第一个比这个元素小的元素
     * 分别进行两次单调栈设计，找出每个元素左边第一个比它小的元素下标 以及右边第一个比它小的元素下标。
     */
    public static int getMaxRecArea(int[] height) {

        Stack<Integer> stack = new Stack<>();

        int[] left = new int[height.length];
        int[] right = new int[height.length];

        stack.push(0);

        // 依次考察每个元素 找出每个元素左边 第一个比它小的元素的下标
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                stack.pop();
            }

            // 栈空的时候要设计成-1 这是细节
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // 两次单调栈
        stack.push(height.length - 1);
        for (int i = height.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                stack.pop();
            }

            right[i] = stack.isEmpty() ? height.length - 1 : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < height.length; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * height[i]);
        }
        return ans;


    }


    /**
     * 直方图能装多少水，比如{3,1,2,4};可以装的水量就是3。给定一个数组，求其对应的直方图能装多少水。
     * <p>
     * 分析下每一个柱子的上面能装多少水取决于它左边最大的柱子和右边最大的柱子的较小者
     * 假定这个较小者的高度为H
     * 当前柱子的高度为height[i]
     * 那么这个柱子上面能装的水量 = (H-height[i]) * 1  算法最终的结果相当于需要把每个柱子上面能装的水量加起来
     * 暴力解法就是每个柱子左边遍历一次找最大，右边遍历一次找最大。
     * <p>
     * <p>
     * <p>
     * 那么怎么提升查找效率呢？
     * (1) 动态规划法（需要正向遍历一次完成 leftMax 数组装配，然后反向一次完成rightMax数组装配）
     * leftMax[i] 代表i位置（含）左边最大的数字
     * leftMax[i] = max{leftMax[i-1], height[i]}
     * rightMax[i] = max{rightMax[i+1], height[i]}
     */
    public static int trapWater(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        for (int i = 0; i < height.length; i++) {
            leftMax[i] = i - 1 < 0 ? height[i] : Math.max(leftMax[i - 1], height[i]);
        }

        for (int i = len - 1; i >= 0; i--) {
            rightMax[i] = i + 1 >= len ? height[i] : Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            res += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return res;
    }


}
