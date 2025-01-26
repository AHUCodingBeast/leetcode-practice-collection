package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/15/下午4:26
 * 原题：
 * 给定一个字符串 text  再给定一个pattern 要求你输出一个boolean类型的值，意为pattern和text是否匹配
 * 「点.」代表任意字符，「星号*」 代表前一个字符重复 0 次或任意次。
 * 例如：aa 与 a* 是匹配的  ab和.*是匹配的
 * <p>
 * <p>
 * 递归设计三步骤：
 * （1）假定我们已经解决了问题，并且有了解决这个问题的函数。（你先定义好你的函数出参和入参）
 * （2）如何将问题从 n 的规模降到 n - 1 的规模。
 * （3）递归的出口
 */
public class Question06 {


    public static void main(String[] args) {
        // false
        System.out.println(isMatch("aabbbc", ".a*bc"));
        // true
        System.out.println(isMatch("aabbbc", ".ab*c"));
        // true
        System.out.println(isMatch("aabbbc", ".*b*c"));
        // true
        System.out.println(isMatch("aabbbc", ".*"));
        // false
        System.out.println(isMatch("abc", "a*"));
    }

    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }

        // 检查文本串和模式串的第一个字符是否匹配
        boolean firstMatch = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        //只有长度大于 2 的时候，才考虑 *
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            /*
             对应的是两种情况：
              情况1: 假定模式串是a*  情况1就是假设a在字符串中一次都没出现过，相当于a*没有匹配意义，这时候需要拿模式串a*之后的部分进行匹配
              情况2: 假定模式串是a*  情况2就是假设a在字符串至少出现1次以上 这时候在字符串第一个已经比较过的前提下显然需要比较字符串第二位以及之后的部分和a* 是否匹配
             */
            return (isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            // 这里也是两种情况
            // 情况1：模式串的长度只有1。这时候实际上isMatch 执行第一轮的时候就会进入递归出口，因为模式串为空了
            // 情况2：模式串的长度大于1，但是不包含 *，这时候就逐位比较就行，也就是比较模式串第一位之后的部分和原串第一位之后的部分
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

}
