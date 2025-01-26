package com.ahu.coding.beast.training.phase01;

import cn.hutool.core.util.StrUtil;

/**
 * @author jianzhang
 * 2025/01/15/下午2:07
 * <p>
 * 原题： 求解字符串里面的最长回文串，例如 cbbdbbdc  输出 bbdbb
 * 这里的思路是转为求解公共最长子串问题+判断回文的问题
 * 马拉车算法没能理解
 * <p>
 * 最长子序列参见
 * {@link Question07}
 * {@code com.ahu.coding.beast.questionfix.Question07#longestCommonSequence(java.lang.String, java.lang.String)}
 */
public class Question04 {
    public static void main(String[] args) {

//        System.out.println(solution("cbbdbbdc"));
//        System.out.println(solution("abbc"));

    }




    public static String solution(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        int len = reverseStr.length();
        int[][] arr = new int[len][len];

        int max = 0;
        int maxIndex = -1;

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < reverseStr.length(); j++) {
                if (str.charAt(i) != reverseStr.charAt(j)) {
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
                    // 记录下最大公共子串的下标 方便最后输出最长公共子串
                    maxIndex = i;
                }
            }
        }
        // abc 和 abd  max =2  maxIndex 1
        String ret = str.substring(maxIndex - max + 1, maxIndex + 1);

        // 因为要求输出回文字符串最长的公共子串有可能不是回文串
        if (isLoopWords(ret)){
            return ret;
        }
        return null;

    }


    /**
     * 判断回文串
     * @param str 目标字符串
     * @return 是否是回文
     */
    public static Boolean isLoopWords(String str) {
        if (StrUtil.isEmpty(str)) {
            return false;
        }
        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                j--;
            }else{
                return false;
            }
        }
        return true;
    }



    /**
     * @apiNote
     * arr[i][j]  表示以字符串a 中a[i]结尾的子串  和 以字符串b 中b[j]结尾的子串  中最长公共子串长度
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
     *
     * @param str 需要进行获取最长回文字符串的目标字符串
     * @return 最长回文子串的长度
     */
    public static int getCommonSubStringLength(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        int len = reverseStr.length();

        int[][] arr = new int[len][len];

        int max = 0;

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < reverseStr.length(); j++) {
                if (str.charAt(i) != reverseStr.charAt(j)) {
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
