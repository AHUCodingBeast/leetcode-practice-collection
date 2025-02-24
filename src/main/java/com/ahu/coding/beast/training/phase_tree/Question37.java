package com.ahu.coding.beast.training.phase_tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author jianzhang
 * 2025/02/21/下午6:28
 * <p>
 * 跳格子3
 * <a href="https://leetcode.cn/problems/jump-game-iii/description/?show=1">原题链接</a>
 * <p>
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 * <p>
 * 示例 1：
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * <p>
 * 分析：
 * 现在咱们分析下这道题 我们发现如果问你下标 i 位置，能不能走到0 ，实际上等价于问你 从 i - arr[i]位置或者i + arr[i]位置能不能走到0，那
 * 问你从 i - arr[i]位置能不能走到0 实际上又可以继续发散，一股递归的味道就扑面而来了：skipGameRes03ByRecursive 就是直接用递归的做法
 * 递归的效率不高 不用说肯定存在重复计算，我们需要考虑能不能用迭代来解决问题
 * 你就会发现这个东西就像个二叉树，每次需要考虑  i - arr[i] 和 i + arr[i] 相当于需要根的左节点和右节点，
 * 只要遍历这个树，考察一棵树的每个节点对应的值是不是0 就行，所以无论DFS和BFS都可以解决问题
 * <p>
 * DFS解法也就是递归解法 参考 skipGameRes03ByDfs
 * BFS解法 参考 skipGameRes03ByBfs
 * <p>
 * <p>
 * 二叉树图示可以参考这里：<a href="https://leetcode.cn/problems/jump-game-iii/solutions/1301145/java-bfs-dfs-qian-xian-yi-dong-fu-shang-mqn6p/?show=1">...</a>
 */
public class Question37 {

    public static void main(String[] args) {

        System.out.println(skipGameRes03(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        System.out.println(skipGameRes03ByBfs(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));

        // 3,0,2,1,2
        System.out.println(skipGameRes03(new int[]{3, 0, 2, 1, 2}, 2));
        System.out.println(skipGameRes03ByBfs(new int[]{3, 0, 2, 1, 2}, 2));


    }

    private static Boolean skipGameRes03(int[] arr, int start) {
        return skipGameRes03ByDfs(arr, start, new HashSet<>());
    }

    private static Boolean skipGameRes03ByDfs(int[] arr, int start, Set<Integer> path) {
        // 避免反复很跳，搞个set记录下标，不要形成环
        if (path.contains(start)) {
            return false;
        }
        if (start >= arr.length || start < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        path.add(start);
        return skipGameRes03ByDfs(arr, start - arr[start], path) || skipGameRes03ByDfs(arr, start + arr[start], path);
    }

    private static Boolean skipGameRes03ByBfs(int[] arr, int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        if (arr[start] == 0) {
            return true;
        }
        queue.addLast(start);
        boolean[] visited = new boolean[arr.length];

        while (!queue.isEmpty()) {
            Integer curIndex = queue.pollFirst();
            if (curIndex < 0 || curIndex >= arr.length) {
                // 注意这里不能写return 遍历到一个节点他的下标超了可能队列里面还有没超范围的下标没考察
                continue;
            }
            if (visited[curIndex]) {
                continue;
            }
            if (arr[curIndex] == 0) {
                return true;
            }
            visited[curIndex] = true;
            queue.addLast(curIndex + arr[curIndex]);
            queue.addLast(curIndex - arr[curIndex]);
        }

        return false;
    }


}
