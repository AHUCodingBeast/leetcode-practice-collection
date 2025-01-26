package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/14/下午5:19
 * Container With Most Water ，直方图能装最多多少水的问题
 * <p>
 * 主要思路
 * 以[1, 8, 6, 2, 5, 4, 8, 3, 7] 先考虑一头一尾 算出来的面积应该是 8 * 1 = 8
 * 对于这样的一个面积 我们如果想着增大它  在长度一定会减少的情况下
 * 如果我们选用短的柱子  最终的面积一定只会比8还小 唯一能增大面积的就是选用更长的柱子，所以哪边柱子短就需要移动哪边的游标，也就是换掉短的一遍的柱子，保留长柱子
 * @see <a href="https://www.bookstack.cn/read/wind-liang-eetcode/ce227cf7dbb84aca.md">...</a>
 */
public class Question08 {
    public static void main(String[] args) {

        // int[] nums = new int[]{10, 8, 2, 3, 11, 5, 1};

        // 49
        int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(waterContainer(nums));

    }

    public static int waterContainer(int[] nums) {

        if (nums == null || nums.length == 1) {
            return -1;
        }

        int i = 0, j = nums.length - 1;

        int maxArea = -1;
        int curArea;

        while (i < j) {
            curArea = (j - i) * Math.min(nums[i], nums[j]);

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
