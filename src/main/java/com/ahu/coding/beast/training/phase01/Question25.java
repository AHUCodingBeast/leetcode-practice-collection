package com.ahu.coding.beast.training.phase01;

import com.ahu.coding.beast.tools.ArrayPrinter;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/02/18/下午5:06
 * <p>
 * 单调栈练习
 * 输入一个数组 nums = [2,1,2,4,3]，你返回数组 [4,2,4,-1,-1]。
 * 因为第一个 2 后面比 2 大的数是 4; 1 后面比 1 大的数是 2；第二个 2 后面比 2 大的数是 4; 4 后面没有比 4 大的数，填 -1；3 后面没有比 3 大的数，填 -1。
 */
public class Question25 {
    public static void main(String[] args) {
        ArrayPrinter.print(calculateGreaterElement(new int[]{2, 1, 2, 4, 3}));
    }


    /**
     * 单调栈模板 请务必理解加记忆
     */
    static int[] calculateGreaterElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

}
