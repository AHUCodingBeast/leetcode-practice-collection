package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/15/下午2:07
 * 将一个整数翻转，例如123翻转为321
 * 注意溢出场景的考虑
 */
public class Question05 {
    public static void main(String[] args) {
        System.out.println(reverseNumber(-1234));
        System.out.println(reverseNumber(1234));
        // 考虑溢出场景
        System.out.println(reverseNumber(1534236496));
    }

    public static int reverseNumber(int number) {
        int ret = 0;
        while (number != 0) {
            int digit = number % 10;
            number = number / 10;
            if (ret > Integer.MAX_VALUE / 10 || ret < Integer.MIN_VALUE / 10) {
                return 0;
            }
            // digit 一定是小于10的
            ret = ret * 10 + digit;
        }
        return ret;
    }

}
