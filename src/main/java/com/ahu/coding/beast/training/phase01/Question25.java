package com.ahu.coding.beast.training.phase01;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/02/18/下午5:06
 * <p>
 *     单调栈练习
 * 输入一个数组 nums = [2,1,2,4,3]，你返回数组 [4,2,4,-1,-1]。
 * 因为第一个 2 后面比 2 大的数是 4; 1 后面比 1 大的数是 2；第二个 2 后面比 2 大的数是 4; 4 后面没有比 4 大的数，填 -1；3 后面没有比 3 大的数，填 -1。
 */
public class Question25 {
    public static void main(String[] args) {

    }


    static int[] calculateGreaterElement(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 倒着往栈里放
        for (int i = n - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                // 矮个起开，反正也被挡着了。。。
                s.pop();
            }
            // nums[i] 身后的更大元素
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }
        return res;
    }

}
