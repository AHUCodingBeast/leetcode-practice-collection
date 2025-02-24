package com.ahu.coding.beast.training.phase_dp;

import java.util.Stack;

/**
 * @author jianzhang
 * 2025/02/20/下午4:35
 * <p>
 * 最短编辑距离问题
 * <p>
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class Question_EditDistance {

    public static void main(String[] args) {
        String word1 = "horse", word2 = "rohe";
//        System.out.println(editDistance(word1, word2));
//
//        System.out.println(editDistanceDp(word1, word2));

        System.out.println(editDistanceDpPro(word1, word2));
    }

    /**
     * 这题的难度比较大
     * 解决两个字符串的动态规划问题，一般都是用两个指针i,j分别指向两个字符串的最后，然后一步步往前走，缩小问题的规模
     * 也就是说我们得分别搞个指针指向word1 和word2 然后执行下述的决策
     * if s1[i] == s2[j]:
     * 啥都别做（skip）
     * i, j 同时向前移动
     * else:
     * 三选一：
     * 插入（insert）
     * 删除（delete）
     * 替换（replace）
     * 在后面的三种操作方法中 其实问题规模就在缩小，可以看下第一种解法 (com.ahu.coding.beast.training.phase_dp.Question_EditDistance#recursive(int, int, java.lang.String, java.lang.String)递归解法
     */

    public static int editDistance(String word1, String word2) {
        return recursive(word1.length() - 1, word2.length() - 1, word1, word2);
    }

    public static Integer recursive(int i, int j, String word1, String word2) {

        // word1 已经到头了 此时另一个字符串还有 j+1 个字符，很显然需要在word1上新增j+1个字符 操作数为 j+1
        if (i == -1) {
            return j + 1;
        }
        // word2 已经到头了 此时另一个字符串还有 i+1 个字符，很显然需要把这i+1个字符删除了 操作数为i+1
        if (j == -1) {
            return i + 1;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            // 第i个和第j个字符是一致的，直接往前考虑
            return recursive(i - 1, j - 1, word1, word2);
        } else {
            // 替换
            int m1 = recursive(i - 1, j - 1, word1, word2) + 1;
            // 新增 在i索引的后面加一个字符这时候 i+1 和 j 就相同了，所以问题变为求解 i和j-1的最短编辑距离
            int m2 = recursive(i, j - 1, word1, word2) + 1;
            // 删除 删除第i索引指向的字符 这时候 变为求解 i-1和j的最短编辑距离
            int m3 = recursive(i - 1, j, word1, word2) + 1;

            return Math.min(Math.min(m1, m2), m3);
        }

    }


    /**
     * 上面的递归还是一样存在重复计算，而且是自顶向下的思维模式，但是本题显然也是可以从下往上的编写程序，于是考虑新建Dp二维数组来解决这个问题
     * dp[i][j] 含义是 word1的前i个字符变为word2的前j个字符需要的最少操作数（也就是编辑距离）
     * <p>
     * 你有没有发现这个dp问题和我们之前讨论的 最长公共子序列，最长公共子串的问题有点类似，都是定义一个dp[i][j]的数组，也是定义了两个游标指针
     * <p>
     * 而且dp[i][j] 总是和他的左边 上边 左上边的元素有关系
     */
    public static int editDistanceDp(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        // 注意下这里要加1 因为我们的定义是前i个字符是从1开始计数的
        int[][] dp = new int[len1 + 1][len2 + 1];

        dp[0][0] = 0;

        // word1的长度为i word2的长度为0  显然编辑距离为i
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        // 理由同上
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 这个方法最终展示了如何具体的得到最终的编辑过程
     */
    public static String editDistanceDpPro(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        // 注意下这里要加1 因为我们的定义是前i个字符是从1开始计数的
        Node[][] dp = new Node[len1 + 1][len2 + 1];


        dp[0][0] = new Node(0, "", null);

        // word1的长度为i word2的长度为0  显然编辑距离为i
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = new Node(i, "word1在索引为" + (i - 1) + "的位置删除:" + word1.charAt(i - 1), OpCode.DELETE);
        }

        // 理由同上
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = new Node(j, "word1在索引为" + (j - 1) + "的位置增加:" + word2.charAt(j - 1), OpCode.ADD);
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    dp[i][j] = new Node(dp[i - 1][j - 1].val, null, null);
                } else {
                    Node delete = new Node(dp[i - 1][j].val + 1, "word1删除索引为:" + (i - 1) + "的字符" + word1.charAt(i - 1), OpCode.DELETE);
                    Node add = new Node(dp[i][j - 1].val + 1, "word1在索引为" + (i - 1) + "的位置增加:" + word2.charAt(j - 1), OpCode.ADD);
                    Node update = new Node(dp[i - 1][j - 1].val + 1, "word1替换索引为:" + (i - 1) + "的字符:" + word1.charAt(i - 1) + "为" + word2.charAt(j - 1), OpCode.UPDATE);

                    if (delete.val < add.val) {
                        dp[i][j] = delete.val < update.val ? delete : update;
                    } else {
                        dp[i][j] = add.val < update.val ? add : update;
                    }
                }
            }
        }


        Stack<String> stack = new Stack<>();
        int i = len1;
        int j = len2;
        Node node = dp[i][j];
        System.out.println(node.val);
        while (node != null && node.val != 0) {
            if (node.opCode == OpCode.DELETE) {
                stack.add(node.choice);
                node = dp[--i][j];
            } else if (node.opCode == OpCode.ADD) {
                stack.add(node.choice);
                node = dp[i][--j];
            } else if (node.opCode == OpCode.UPDATE) {
                stack.add(node.choice);
                node = dp[--i][--j];
            } else {
                node = dp[--i][--j];
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append("\n");
        }

        return result.toString();

    }


    enum OpCode {
        DELETE(1),
        ADD(2),
        UPDATE(3);

        private int code;

        OpCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    static class Node {
        int val;
        String choice;
        OpCode opCode;


        public Node(int val, String choice, OpCode opCode) {
            this.val = val;
            this.choice = choice;
            this.opCode = opCode;
        }
    }

}
