package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/02/27/下午5:45
 * <p>
 * 在数组中，数字减去它右边的数字得到一个数对之差。求所有数对之差的最大值。例如在数组{2, 4, 1, 16, 7, 5, 11, 9}中，数对之差的最大值是11，是16减去5的结果 ，请你输出目标数组的最大数对之差
 */
public class Question45 {

    public static void main(String[] args) {
        System.out.println(getMaxGap(new int[]{2, 4, 1, 16, 7, 5, 11, 9}));
    }

    /**
     * 这题和卖股票的那个题目其实非常像，我们依次求解每个数作为减数的最大数对差，然后找出最大的数对差即可
     * 那么对于每个数作为减数的最大数对差怎么求解呢，只要在遍历的过程中记录最大值，然后后拿最大值减掉当前数字就行
     */
    public static int getMaxGap(int[] nums) {
        int maxGapDiff = 0;
        int maxVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int currentGap = maxVal - nums[i];
            if (currentGap > maxGapDiff) {
                maxGapDiff = currentGap;
            }
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }

        return maxGapDiff;
    }

}
