package com.ahu.coding.beast.training.phase01;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/02/28/上午11:39
 * <p>
 * 题目：请你使用递归的写法让一个栈里面的元素逆序放置
 * 例如一个栈依次压入1,2,3,4,5，那么从栈顶到栈底分别为5,4,3,2,1。现在要求你写一段代码将这个栈的元素逆序，即变为从栈顶到栈底顺序为1,2,3,4,5.要求是只能使用递归实现，不能使用其他的数据结构。
 */
public class Question46 {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reverse(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 移除栈底元素
        int i = getAndRemoveLastElement(stack);
        // 把其他的元素都逆转一下
        reverse(stack);
        // 再把这个元素放到栈顶
        stack.push(i);
    }

    /**
     *递归移除并返回栈中栈底元素
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }


}
