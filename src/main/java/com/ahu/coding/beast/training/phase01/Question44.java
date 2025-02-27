package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/02/27/下午4:20
 */
public class Question44 {

    /**
     * 题目：小球从高为H米的位置下落，每次落地后反跳回上次下落高度的一半。问直到小球不跳，小球总共走过的路程是多少米；数字要求都为整数
     */
    public static int calDis(double height) {
        if (height == 0) {
            return 0;
        } else {
            return (int) (height + height / 2 + calDis(height / 2));
        }
    }

    /**
     * 题目：在一组数的编码中，若任意两个相邻的代码只有一位二进制数不同， 则称这种编码为格雷码(Gray Code)，给定一个整数n，请返回n位的格雷码，顺序为从0开始
     * 比如求n=3的gray码，首先知道n=2的gray码是(00,01,11,10)  那么n=3的gray码其实就是对n=2的gray码首位添加0或1生成的，添加0后变成(000,001,011,010) 添加1后需要顺序反向就变成(110,111,101,100)
     * 组合在一起就是(000,001,011,010,110,111,101,100)。特别需要注意的就是这个反向，要是不反向合并的时候就不符合grey码相邻位置只有一位二进制不同的要求了。
     */
    public static String[] getGray(int n) {
        String result[] = null;
        if (n == 1) {
            result = new String[]{"0", "1"};
        } else {
            // 得到n-1位的格雷码
            String[] temp = getGray(n - 1);
            // 下一次数组的个数会是上一次的2倍
            result = new String[temp.length * 2];
            // 在n-1位的格雷码集合中分别在头部添加1或者是0
            for (int i = 0; i < temp.length; i++) {
                result[i] = "0" + temp[i];
                // 添加1之后把这个组合后的字符串扔到后面。
                result[result.length - 1 - i] = "1" + temp[i];
            }
        }
        return result;
    }


}
