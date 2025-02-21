package com.ahu.coding.beast.training.phase01;

import com.ahu.coding.beast.tools.ArrayPrinter;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author jianzhang
 * 2025/02/20/下午6:50
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
public class Question36 {
    public static void main(String[] args) {
        ArrayPrinter.print(maxSlidingWindow01(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    /**
     * 这个题目首先数列是无序的显然也不能排序，这时候你就不能照搬滑动窗口的模板代码了
     * 这里介绍的第一种方案就是使用一种单调队列来解决这个问题，单调队列就是队列里面的元素都是 非严格增序或者非严格降序的
     * 思路参考：<a href="https://labuladong.online/algo/data-structure/monotonic-queue/">...</a>
     * <p>
     * 本题单调队列里面记录的实际上是数组下标
     */
    public static int[] maxSlidingWindow01(int[] nums, int k) {

        // 首先判断最终的结果应该有多少个 很显然是 n - k + 1
        int[] res = new int[nums.length - k + 1];
        int index = 0;

        Deque<Integer> monoQueue = new LinkedList<>();

        // 先处理第一个窗口内的数据
        for (int i = 0; i < k; i++) {
            while (!monoQueue.isEmpty() && nums[i] >= nums[monoQueue.peekLast()]) {
                monoQueue.pollLast();
            }
            monoQueue.addLast(i);
        }
        res[index++] = nums[monoQueue.peekFirst()];


        // 定义窗口的左边界和右边界 全部是闭区间
        int wndLeft = 1;
        int wndRight = wndLeft + k - 1;

        while (wndRight < nums.length) {

            // 移出不在窗口之内的元素
            while (!monoQueue.isEmpty() && monoQueue.peekFirst() <= wndLeft - 1) {
                monoQueue.pollFirst();
            }

            // 恶霸入队，弱小之人通通闪开
            while (!monoQueue.isEmpty() && nums[wndRight] >= nums[monoQueue.peekLast()]) {
                monoQueue.pollLast();
            }
            monoQueue.addLast(wndRight);

            // 记录当前窗口最牛逼的人
            res[index++] = nums[monoQueue.peekFirst()];

            // 移动窗口
            wndLeft++;
            wndRight++;
        }

        return res;

    }

    static class MonotonicQueue {
        // 双链表，支持快速在头部和尾部增删元素
        // 维护其中的元素自尾部到头部单调递增
        private LinkedList<Integer> maxq = new LinkedList<>();

        // 在尾部添加一个元素 n，维护 maxq 的单调性质
        public void addLast(int number) {
            // 将前面小于自己的元素都删除
            while (!maxq.isEmpty() && maxq.getLast() < number) {
                maxq.pollLast();
            }
            maxq.addLast(number);
        }

        public int max() {
            // 队头的元素肯定是最大的
            return maxq.getFirst();
        }

        public void pop() {
            if (!maxq.isEmpty()) {
                maxq.removeFirst();
            }
        }

    }


}
