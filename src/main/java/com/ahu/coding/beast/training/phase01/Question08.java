package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/14/下午5:19
 * 直方图的最大面积：
 * 有一个直方图，用一个整数数组表示，其中每列的宽度为1，求所给直方图包含的最大矩形面积。比如，对于直方图[2,7,9,4], 它所包含的最大矩形的面积为14(即[7,9]包含的7x2的矩形)。
 * <p>
 * 面积大小只取决于边界的两个柱子 这个你首先要清楚
 * 现在我们定义一个游标i指向索引0位置 再定义一个游标j执行最后一个位置
 * 对于这样的一个面积 我们如果想着增大它 在长度一定会减少的情况下，增大面积的唯一办法就是使得短的边界变得更大一点，这也就决定了i和j怎么向中间靠拢
 * @see <a href="https://www.bookstack.cn/read/wind-liang-eetcode/ce227cf7dbb84aca.md">...</a>
 */
public class Question08 {
    public static void main(String[] args) {

        // int[] nums = new int[]{10, 8, 2, 3, 11, 5, 1};

        // 49
        int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(waterContainer(nums));

        int[] nums2 = new int[]{2, 7, 9, 4, 1};
        System.out.println(waterContainer(nums2));

    }

    public static int waterContainer(int[] nums) {

        if (nums == null || nums.length == 1) {
            return -1;
        }

        int i = 0, j = nums.length - 1;

        int maxArea = -1;
        int curArea;

        while (i < j) {
            curArea = (j - i + 1) * Math.min(nums[i], nums[j]);

            if (curArea > maxArea) {
                maxArea = curArea;
            }

            if (nums[i] > nums[j]) {
                j--;
            } else {
                i++;
            }
        }

        return maxArea;
    }
}
