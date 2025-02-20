package com.ahu.coding.beast.tools;

/**
 * @author jianzhang
 * 2025/02/20/下午7:37
 */
public class ArrayPrinter {
    public static void print(int[] array) {
        StringBuilder res = new StringBuilder();
        if (array == null) {
            System.out.println("数组为空");
            return;
        }
        for (int t : array) {
            res.append(t).append(" ");
        }
        System.out.println(res);
    }
}
