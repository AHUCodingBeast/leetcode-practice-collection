package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/03/05/上午11:11
 * <p>
 * <p>
 * 给定一个数组长度大于2 ，找出不相交的两个子数组，情况是很多的 请返回这么多情况中，两个不相交子数组的最大的和.
 * <p>
 * 举例：-1,3,4,-9,1,2  第一个子数组为{3,4} 第二个子数组为{1,2} 这样两个子数组的最大的和为10
 */
public class Question47 {


    public static void main(String[] args) {
        int[] arr = new int[]{-1, 3, 4, -9, 1, 2};
        System.out.println(maxSubArrayAdd(arr));
    }


    public static int maxSubArrayAdd(int[] arr) {

        int maxSum = Integer.MIN_VALUE;

        // 考察每一种划分  [0,i] [i+1,arr.length-1] 下最大子数组的和
        for (int i = 1; i < arr.length - 1; i++) {
            int sum1 = subArrayMaxSum(arr, 0, i);
            int sum2 = subArrayMaxSum(arr, i + 1, arr.length - 1);
            int sum = sum1 + sum2;
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }


    /*
     * 求解begin->end 范围内最大和子数组
     */
    public static int subArrayMaxSum(int[] arr, int begin, int end) {


        int pre = arr[begin];
        int maxSum = arr[begin];

        for (int i = begin + 1; i <= end; i++) {
            int cur = pre > 0 ? pre + arr[i] : arr[i];
            if (cur > maxSum) {
                maxSum = cur;
            }
            pre = cur;
        }
        return maxSum;
    }


}



