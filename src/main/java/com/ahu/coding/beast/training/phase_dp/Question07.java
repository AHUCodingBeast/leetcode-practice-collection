package com.ahu.coding.beast.training.phase_dp;

import cn.hutool.core.util.StrUtil;
import com.ahu.coding.beast.training.phase01.Question04;

/**
 * @author jianzhang
 * 2025/01/15/下午5:16
 * <p>
 * 求解两个字符串的最长公共子序列
 * <p>
 * 最长公共子串的问题参考：{@link Question04 }
 */
public class Question07 {

    public static void main(String[] args) {

        System.out.println(longestCommonSequence("abcd", "acbcfd"));
        System.out.println(longestCommonSequence("abcd", null));
        System.out.println(longestCommonSequence("13456778", "357486782"));

    }


    /**
     * @param str1 字符串x
     * @param str2 字符串y
     * @return 最长公共子序列的值
     * @apiNote 分析：
     * arr[i][j]  表示以字符串x 前i 个元素，和字符串y 前j个元素 的最长公共子序列的长度 （这个定义多读几遍）
     * <p>
     * 假定字符串x是 abcd  字符串y是 abfcdf  则x与y的 最长公共子序列长度为abcd
     * 作为对比可以发现arr[i][j]的含义和最长公共子串的问题是不同的
     * <p>
     * 递推公式为：
     * arr[i][j] = arr[i-1][j-1] + 1   仅当   a[i]=b[j]
     * arr[i][j] = max{arr[i][j-1] , arr[i-1][j]}   仅当   a[i]!=b[j]
     * <p>
     * 这个递推公式怎么来的呢？其实可以写一个二维数组自己去填表来发现规律
     * 例如求解 X: abcd 和 Y: acbcfd的公共子串长度，arr[i][j] 的结果如下：
     * arr[3][2]=3 为什么呢 因为acbcfd的前四个字符为 acbc，abcd的前3个字符为 abc ，最长公共子序列显然是abc，所以这里长度为3
     *      a(0) b(1) c(2) d(3)
     * a(0)   1   1   1   1
     * c(1)   1   1   2   2
     * b(2)   1   2   2   2
     * c(3)   1   2   3   3
     * f(4)   1   2   3   3
     * d(5)   1   2   3   4
     * <p>
     * 那现在问题最终就是求解这样的一个二维数组，并在填充这个二维数组的时候记录下最大的值，那么最长的子序列的值确实找到了，怎么求解最长的公共子序列呢
     * <p>
     * 这就要在这个动态规划数组中找出一条路径来回溯出最长子序列。具体回溯规则是
     * X[i]==Y[j] 则把这个元素加到最长子序列里面，同时向arr[i-1][j-1]方向移动，因为按照前面的递推定义 去掉X[i]和Y[j]之后的最长子序列长度和arr[i-1][j-1] 是一样的
     * X[i]!=Y[j] 则向大的数值方向移动，也就是往 max{arr[i][j-1] , arr[i-1][j]} 移动，如果两者相同则随便选个方向移动即可比如向上移动
     * 比如上面的例子，移动路径为
     * arr[5][3] (添加d) -> arr[4][2] -> arr[3][2](添加c) -> arr[2][1](添加b) -> arr[1][0] -> arr[0][0] (添加a)
     * <p>
     * 实现代码的时候需要考虑下移动到边界的特殊处理,和数组下标的合法性
     */
    public static String longestCommonSequence(String str1, String str2) {

        if (StrUtil.isEmpty(str1) || StrUtil.isEmpty(str2)) {
            return null;
        }

        int max = -1;
        int maxI = -1;
        int maxJ = -1;

        int[][] arr = new int[str1.length()][str2.length()];

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    arr[i][j] = (i - 1 < 0 || j - 1 < 0) ? 1 : (arr[i - 1][j - 1] + 1);
                } else {
                    int t1 = i - 1 < 0 ? 0 : arr[i - 1][j];
                    int t2 = j - 1 < 0 ? 0 : arr[i][j - 1];
                    arr[i][j] = Math.max(t1, t2);
                }
                if (max < arr[i][j]) {
                    max = arr[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }


        StringBuilder result = new StringBuilder();
        if (max > 0) {
            // 开始回溯
            int i = maxI;
            int j = maxJ;

            while (i >= 0 || j >= 0) {
                if (i >= 0 && j >= 0 && str1.charAt(i) == str2.charAt(j)) {
                    result.append(str1.charAt(i));
                    i--;
                    j--;
                } else {
                    int t1 = (i - 1 < 0 || j < 0) ? 0 : arr[i - 1][j];
                    int t2 = (j - 1 < 0 || i < 0) ? 0 : arr[i][j - 1];
                    if (t1 < t2) {
                        j--;
                    } else {
                        i--;
                    }
                }
            }
        }


        return result.reverse().toString();
    }
}
