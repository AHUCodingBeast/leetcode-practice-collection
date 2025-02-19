package com.ahu.coding.beast.training.phase01;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 关联例题   {@link Question01}
 * <p>
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 * @author jianzhang
 */
public class Question24 {
    public static void main(String[] args) {

        System.out.println(threeSumDoublePoint01(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSumDoublePoint02(new int[]{-1, 0, 1, 2, -1, -4}));

    }


    /**
     * 咱们分析下这道题
     * （1）组合思路：首先这道题目转化为组合问题 一定是可以解决的，遍历组合树上面的所有节点 观察它的和是不是0  然后再收集起来，
     * 问题就结束了，但是注意由于组合里面有重复的数字所以需要涉及剪枝
     * （2）双指针思路：前面我们利用双指针解决过两数之和的问题，这题乍一看不能用双指针，那能不能转为双指针呢？
     * 假定 nums[i] + nums[j] + nums[k] = 0 那是不是就相当于找出两个数的和等于 -num[k]  于是问题又重新转为2sum问题
     * 接着我们再考虑怎么避免重复，问一个关键问题就是这里的left指针 初始化的时候 应该是0 还是 i
     * 我们枚举的三元组(a,b,c)满足a≤b≤c，保证了只有(a,b,c)这个顺序会被枚举到，而(b,a,c)、(c,b,a)等等这些不会，这样就减少了重复
     * 基于上面的这个事实我们就知道初始化的时候应该初始化为i
     */
    public static List<List<Integer>> threeSumDoublePoint01(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int curSum = nums[left] + nums[right];

                if (curSum == target) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    // 还要继续寻找 因为要穷举所有组合
                    // 有个关键点就是如果这里出现了num[left] 和 num[left+1] 相同的场景 result里面就可能会添加多次一样的组合所以这里需要跳过
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }

                if (curSum > target) {
                    right--;
                    continue;
                }

                if (curSum < target) {
                    left++;
                }
            }
        }
        return result;
    }


    public static List<List<Integer>> threeSumDoublePoint02(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        doTrace(nums, 0, new ArrayList<>(), result);

        return result;

    }

    public static void doTrace(int[] nums, int start, List<Integer> trace, List<List<Integer>> result) {

        if (trace.size() == 3) {
            int sum = 0;
            for (Integer num : trace) {
                sum += num;
            }
            if (sum == 0) {
                result.add(new ArrayList<>(trace));
            }
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            if (trace.size() > 3) {
                continue;
            }
            trace.addLast(nums[i]);
            doTrace(nums, i + 1, trace, result);
            trace.removeLast();
        }

    }


}
