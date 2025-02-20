package com.ahu.coding.beast.training.phase01;

import com.ahu.coding.beast.tools.ArrayPrinter;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/02/18/下午5:31
 * <p>
 * 单调栈练习
 * 给你一个数组 temperatures，这个数组存放的是近几天的天气气温，你返回一个等长的数组，计算：对于每一天，你还要至少等多少天才能等到一个更暖和的气温；如果等不到那一天，填 0。函数签名如下：
 * 比如说给你输入 temperatures = [73,74,75,71,69,76]，你返回 [1,1,3,2,1,0]。因为第一天 73 华氏度，第二天 74 华氏度，比 73 大，所以对于第一天，只要等一天就能等到一个更暖和的气温，后面的同理。
 */
public class Question27 {
    public static void main(String[] args) {

        ArrayPrinter.print(temperature(new int[]{73,74,75,71,69,76}));
    }

    /**
     * 完全套用单调栈模板 没什么好说的
     */
    public static int[] temperature(int[] temperatures) {

        Stack<TemperatureNum> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().temperature <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : (stack.peek().index - i);
            stack.push(new TemperatureNum(temperatures[i], i));
        }

        return res;
    }

    static class TemperatureNum {
        int temperature;
        int index;

        public TemperatureNum(int temperature, int index) {
            this.temperature = temperature;
            this.index = index;
        }
    }

}
