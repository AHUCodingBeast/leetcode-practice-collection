package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/02/18/下午5:28
 * <p>
 * 题目：给定一个长度为n的数组arr ，这个数组可以划分为左右两个部分，
 * 左部分是arr[0...K],
 * 右部分是arr[K+1...n-1] 。
 * 现在欲找到一种划分，使得左边数组的最大值减去右边数组的最大值的差的绝对值最大，求该差值的最大值，要求时间复杂度O(n)
 * <p>
 *     分析：
 * 这个题目实际上是个智力题，原数组arr的最大值要么分在了左边要么分在了右边
 * 假如最大值分在了左边， 此时的划分可能如下
 * x x x x MAX x x || o o o
 * 这时候对于右边的三个元素而言 如果 前两个比最后一个大 差值就不会是最大（肯定不是最佳划分），如果前面两个都比最后一个小 那么差值情况就和下面的这种划分是等价的
 * x x x x MAX x x x x || o
 * 所以实际上最大值划到一边之后，我们只需要考虑另一边只有边界值的情况即可，总结下来是我们只需要考虑下面两种情况
 * <p>
 * x x x x MAX x x x x || o
 * x || o o o o MAX o o o o
 */
public class Question48 {

    public int getDivideMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int max = arr[0];
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }
        return Math.max(Math.abs(max - arr[0]), Math.abs(max - arr[arr.length - 1]));
    }

}
