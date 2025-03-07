package com.ahu.coding.beast.training.phase_dp;

import com.ahu.coding.beast.tools.ArrayPrinter;

import java.util.*;

/**
 * @author jianzhang
 * 2025/02/27/下午2:23
 * <p>
 * 题目：
 * 路径上的最大收益：
 * 小东所在公司要发年终奖，而小东恰好获得了最高福利，他要在公司年会上参与一个抽奖游戏，
 * 游戏在一个6*6的棋盘上进行，上面放着36个价值不等的礼物，每个小的棋盘上面放置着一个礼物，他需要从左上角开始游戏，每次只能向下或者向右移动一步，到达右下角停止，一路上的格子里的礼物小东都能拿到，
 * 请设计一个算法使小东拿到价值最高的礼物。给定一个6*6的矩阵，其中每个元素为对应格子的礼物价值,左上角为[0,0],请返回能获得的最大价值，保证每个礼物价值大于100小于1000。
 */
public class Question43 {

    public static void main(String[] args) {

        int nums[][] = new int[6][6];


        nums = new int[][]{
                {3, 5, 8, 9, 9, 5},
                {6, 6, 2, 4, 0, 9},
                {4, 2, 7, 4, 4, 6},
                {9, 2, 2, 4, 5, 2},
                {2, 0, 6, 4, 0, 1},
                {5, 3, 2, 5, 4, 3}
        };

        ArrayPrinter.print(nums);
        System.out.println(getMaxPathValueDpRight(nums));

    }

    /**
     * 这题DP思路显而易见
     * 定义 dp[i][j] 为走到（i，j） 位置时的最大收益
     * dp[i][j] = max{dp[i-1][j] + num[i][j] ,  dp[i][j-1] + num[i][j]}
     * <p>
     * 但是这题最需要注意的就是考虑i=0或者j=0的场景 ，如果没有合适的初始化，结果就不对 例如 getMaxPathValueDpWrong 方法就没有正确的初始化数组导致最终结果不对
     * 为什么不对呢？
     * 首先因为dp数组的每个元素存在默认值0
     * 假设矩阵 matrix 第一行为 [1, 2, 3]，若不初始化第一行，dp[0][1] 本应是 dp[0][0] + matrix[0][1] = 1 + 2 = 3，但由于 dp[0][0] 未正确初始化（默认 0），会错误计算为 0 + 2 = 2。
     * getMaxPathValueDpWrong 没有正确初始化第一行和第一列的dp值，这就导致后续的DP值计算错误
     */

    public static int getMaxPathValueDpRight(int[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];

        // key:当前节点  value: 父节点
        Map<Node, Node> link = new LinkedHashMap<>();
        link.put(new Node(0, 0, matrix[0][0]), null);

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {

                    dp[i][j] = matrix[0][0];

                } else if (i == 0) {

                    dp[i][j] = dp[i][j - 1] + matrix[i][j];
                    link.put(new Node(i, j, matrix[i][j]), new Node(i, j - 1, matrix[i][j - 1]));

                } else if (j == 0) {

                    dp[i][j] = dp[i - 1][j] + matrix[i][j];
                    link.put(new Node(i, j, matrix[i][j]), new Node(i - 1, j, matrix[i - 1][j]));

                } else {

                    // dp[i][j] = max{dp[i-1][j] + num[i][j] ,  dp[i][j-1] + num[i][j]}
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j] + matrix[i][j];
                        link.put(new Node(i, j, matrix[i][j]), new Node(i - 1, j, matrix[i - 1][j]));
                    } else {
                        dp[i][j] = dp[i][j - 1] + matrix[i][j];
                        link.put(new Node(i, j, matrix[i][j]), new Node(i, j - 1, matrix[i][j - 1]));
                    }
                    
                }
            }
        }

        List<String> path = new ArrayList<>();
        Node cur = new Node(dp.length - 1, dp[0].length - 1, matrix[dp.length - 1][dp[0].length - 1]);
        while (cur != null) {
            path.add(cur.toString());
            cur = link.get(cur);
        }
        Collections.reverse(path);
        System.out.println(String.join("->", path));


        return dp[dp.length - 1][dp[0].length - 1];
    }


//    public static int getMaxPathValueDpWrong(int[][] matrix) {
//        int[][] dp = new int[matrix.length][matrix[0].length];
//        dp[0][0] = matrix[0][0];
//        dp[0][1] = matrix[0][0] + matrix[0][1];
//        dp[1][0] = matrix[1][0] + matrix[0][0];
//
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 1; j < dp[0].length; j++) {
//                if (dp[i - 1][j] > dp[i][j - 1]) {
//                    dp[i][j] = dp[i - 1][j] + matrix[i][j];
//                } else {
//                    dp[i][j] = dp[i][j - 1] + matrix[i][j];
//                }
//            }
//        }
//        return dp[dp.length - 1][dp[0].length - 1];
//    }


    static class Node {
        int x;
        int y;
        int val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,[%d])", x, y, val);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node node = (Node) obj;
                return node.x == this.x && node.y == this.y && node.val == this.val;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, val);
        }
    }

}
