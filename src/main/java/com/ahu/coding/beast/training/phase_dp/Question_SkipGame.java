package com.ahu.coding.beast.training.phase_dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author jianzhang
 * 2025/02/18/下午4:36
 * 跳格子问题 专项
 * {@link com.ahu.coding.beast.training.phase_tree.Question37}
 */
public class Question_SkipGame {

    public static void main(String[] args) {
        System.out.println(skipGameRes01(new int[]{3, 2, 1, 0, 4}));
        System.out.println(skipGameRes01(new int[]{2, 3, 1, 1, 4}));
        System.out.println(skipGameRes02Pro(new int[]{1, -1, -2, 4, -7, 3}, 2));

    }

    /**
     * 题目1：<a href="https://leetcode.cn/problems/jump-game/description/">...</a>
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     * 例如
     * 给定 [2,3,1,1,4] 应该输出true 可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * 给定 [3,2,1,0,4] 应该输出false  无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标
     *
     * <p>
     * 分析：
     * 对于数组中的任意一个位置y，我们如何判断它是否可以到达？
     * 很简单 我们只要找出一个x 使得 即x+nums[x]≥y，那么位置y就可以到达。
     * <p>
     * 位置i 能最多跳 nums[i] 步 也就是说可以到达的范围是 [i+1 , i+nums[i]] 到底跳几步呢 ？
     * 有个朴素的想法就是我如果每一次都跳到的格子能使得都选下一次能跳的更远就可以
     * 比如说 上面的例子 一开始从位置0 开始，最多可以跳2步，也就是可以跳到1号位置也可以跳到2号位置 我们选择跳到1号位置 为啥呢 因为选择1号位置下一次能跳三步也就是跳的更远
     * <p>
     * 具体到代码实现层面怎么写呢？
     * 如果某一个作为起跳点的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为起跳点
     * 可以对每一个能作为起跳点的格子都尝试跳一次，把能跳到最远的距离不断更新
     * 如果可以一直跳到最后，就成功了
     */
    private static boolean skipGameRes01(int[] nums) {
        int maxReachPosition = 0;
        int cur = 0;
        while (cur <= maxReachPosition) {
            int curMaxReachPosition = cur + nums[cur];
            if (curMaxReachPosition > maxReachPosition) {
                maxReachPosition = curMaxReachPosition;
            }
            if (maxReachPosition >= nums.length - 1) {
                return true;
            }
            cur++;
        }
        return false;
    }


    /**
     * 跳格子2
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
     * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
     * 请你返回你能得到的 最大得分 。
     * <p>
     * 示例 1：
     * 输入：nums = [1,-1,-2,4,-7,3], k = 2
     * 输出：7
     * 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
     * <p>
     * 示例 2：
     * 输入：nums = [10,-5,-2,4,0,3], k = 3
     * 输出：17
     * 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
     * <p>
     * <a href="https://leetcode.cn/problems/jump-game-vi/description/?show=1">原题链接</a>
     * <p>
     * 分析
     * 这道题首先数组可以有负数了 接下来每次跳的最远距离是限定范围K的
     */
    private static int skipGameRes02(int[] nums, int k) {

        // 用dp[i]来表示到达位置i的最大得分。初始状态dp[0]=nums[0]，表示位置0的得分是它本身的得分。
        // 递推公式为： dp[i] = max{dp[i-1] ,dp[i-2] ....dp[i-k] } + nums[i]  当然了 i-k不能小于0 造成数组越界
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < dp.length; i++) {
            int max = dp[i - 1] + nums[i];
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0 && max < dp[i - j] + nums[i]) {
                    max = dp[i - j] + nums[i];
                }
            }
            dp[i] = max;
        }
        return dp[nums.length - 1];
    }

    /**
     * 上面的  skipGameRes02 需要一个额外的for循环来找出最大值，这在leetcode中无法通过大数据量测试
     * 需要想办法优化这里的第二个for循环,具体怎么做呢?
     * 还是借助单调队列，只不过我们这次把索引放进去而不是把具体的数值放进去
     */
    private static int skipGameRes02Pro(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        deque.push(0);
        for (int i = 1; i < dp.length; i++) {

            // 首先把不在窗口范围内的元素移出去
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // 单调队列的第一个元素就是最大dp数组的下标
            dp[i] = dp[deque.peekFirst()] + nums[i];

            // 把比当前dp[i]小的元素都压扁
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }

            deque.add(i);

        }
        return dp[nums.length - 1];
    }





}
