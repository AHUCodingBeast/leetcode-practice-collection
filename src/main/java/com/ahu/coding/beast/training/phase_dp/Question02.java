package com.ahu.coding.beast.training.phase_dp;

/**
 * @author jianzhang
 * 2025/02/08/下午2:56
 * <p>
 * 求解两个字符串的最长公共子串的长度
 */
public class Question02 {
    public static void main(String[] args) {

        System.out.println(getCommonSubStringLength("abcd","bcda"));

    }


    /**
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最长公共子串的长度
     * @apiNote arr[i][j]  表示以字符串a 中a[i]结尾的子串  和 以字符串b 中b[j]结尾的子串  中最长公共子串长度
     * 请务必好好理解arr[i][j]的定义,读上面的定义至少三遍
     * 举例 ：
     * a字符串为 abc ，b字符串为 abd 则按照上述定义 arr[2][2]=0 arr[1][1]=2
     * arr[2][2]=0 为什么呢？因为a字符串中以c结尾的子串有 abc c bc ，b字符串中以d结尾的子串有abd d bd 所以两者没有公共子串，自然长度为0
     * arr[1][1]=2 因为a字符串中以b结尾的子串有 ab b ， b字符串中以b结尾的子串有 ab b 所以两者有最长的公共子串ab 长度为2
     * <p>
     * 在理解arr[i][j]的定义的基础上我们就可以发现下述规律
     * <p>
     * 那就是如果a[i]和b[j]不相等，则肯定最长公共子串的长度就是0 （因为子串要连续）
     * 如果a[i]和b[j]相等  则 arr[i][j] = arr[i-1][j-1] + 1
     */
    public static int getCommonSubStringLength(String str1, String str2) {

        int[][] arr = new int[str1.length()][str2.length()];

        int max = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) != str2.charAt(j)) {
                    arr[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        return max;
    }
}
