package com.ahu.coding.beast.training.phase01;

/**
 * @author jianzhang
 * 2025/01/24/下午6:06
 * <p>
 * 回溯算法：
 * 解决一个回溯问题，实际上就是遍历一棵决策树的过程，树的每个叶子节点存放着一个合法答案。你把整棵树遍历一遍，把叶子节点上的答案都收集起来，就能得到所有的合法答案
 * 题目：
 * 给你输入一个数组 nums，让你返回这些数字的全排列
 *
 * 分析：
 * 先固定第一位为 1，然后第二位可以是 2，那么第三位只能是 3；然后可以把第二位变成 3，第三位就只能是 2 了；然后就只能变化第一位，变成 2，然后再穷举后两位
 *
 * 在草稿纸上画一画 这是典型的N个分叉的树结构 所以本质上就是遍历这个树上面的所有节点
 * @see <a href="https://labuladong.online/algo/essential-technique/backtrack-framework/#%E5%85%A8%E6%8E%92%E5%88%97%E9%97%AE%E9%A2%98%E8%A7%A3%E6%9E%90">回溯算法</a>
 * for 选择 in 选择列表:
 *     # 做选择
 *     将该选择从选择列表移除
 *     路径.add(选择)
 *     backtrack(路径, 选择列表)
 *     # 撤销选择
 *     路径.remove(选择)
 *     将该选择再加入选择列表
 */
public class Question02 {


    public static void main(String[] args) {

    }

}
