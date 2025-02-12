package com.ahu.coding.beast.training.phase01;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/02/12/上午10:19
 * <p>
 * 题目：判定一个序列是不是合法的出栈序列
 * 举例：
 * 假定 入栈序列为 1 2 3 4 5，
 * 则 1 2 3 4 5可能为它的出栈序列，（1进栈然后1出栈，2进栈然后2出栈...）
 * <p>
 * 而 5 4 1 2 3不可能为它的出栈序列。(5先出栈的情况下1234肯定已经进栈，所以不可能出现1,2,3的出栈序列)
 */
public class Question19 {
    public static void main(String[] args) {
        int[] pushOrder = {1, 2, 3, 4, 5};
        int[] popOrder1 = {4, 5, 3, 2, 1};
        int[] popOrder2 = {4, 3, 5, 1, 2};
        System.out.println("Pop order 1 is " + (isLegalOutStackSeq(pushOrder, popOrder1)? "valid" : "invalid"));
        System.out.println("Pop order 2 is " + (isLegalOutStackSeq(pushOrder, popOrder2)? "valid" : "invalid"));


    }

    public static boolean isLegalOutStackSeq(int[] seqIn, int[] seqOut) {

        Stack<Integer> stack = new Stack<>();
        // 注意这个游标一定要定义在for循环的外边
        int k = 0;
        for (int cur : seqOut) {
            // 如果当前栈顶元素和要出栈的元素不同
            // 则将剩余的输入序列中的元素依次压入栈中，直到栈顶元素与输出序列当前元素相同或输入序列遍历完毕
            while (stack.isEmpty() || stack.peek() != cur) {
                if (k >= seqIn.length) {
                    return false;
                }
                stack.push(seqIn[k++]);
            }
            stack.pop();
        }
        return true;
    }

}
