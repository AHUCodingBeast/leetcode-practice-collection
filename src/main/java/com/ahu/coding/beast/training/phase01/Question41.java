package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/02/25/下午3:54
 * <p>
 * 用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class Question41 {

    public static void main(String[] args) {
        System.out.println(coverNum(2));
    }


    public static int coverNum(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        //  先拿一块2*1的竖着放，或者先拿一块横着放（当横着放时其下面的部分也就确定了）
        return coverNum(n - 1) + coverNum(n - 2);
    }

}
