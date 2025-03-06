package com.ahu.coding.beast.training.phase_tree;

import com.ahu.coding.beast.entity.BinaryTreeNode;
import com.ahu.coding.beast.tools.BinaryTreeUtils;

/**
 * @author jianzhang
 * 2025/01/26/上午11:52
 */
public class BinaryTreeQuestion01 {

    public static void main(String[] args) {
        int[] nums = {11, 12, 5, 6, 20, 23, 45, 22, 9};
        // 构建一棵平衡二叉树
        BinaryTreeNode binaryTreeNode = BinaryTreeUtils.buildBalanceBinaryTree(nums, 0, nums.length - 1);

        // 格式化打印
        System.out.println("当前树的结构为：");
        BinaryTreeUtils.prettyFormatTree(binaryTreeNode);

        System.out.println("中序遍历=" + BinaryTreeUtils.traverseBinaryTree(binaryTreeNode, BinaryTreeUtils.BinaryTreeTraverseTypeEnum.MID_ORDER));
        System.out.println("后序遍历=" + BinaryTreeUtils.traverseBinaryTree(binaryTreeNode, BinaryTreeUtils.BinaryTreeTraverseTypeEnum.LAST_ORDER));
        System.out.println("前序遍历=" + BinaryTreeUtils.traverseBinaryTree(binaryTreeNode, BinaryTreeUtils.BinaryTreeTraverseTypeEnum.PRE_ORDER));
        System.out.println("层次遍历=" + BinaryTreeUtils.traverseBinaryTree(binaryTreeNode, BinaryTreeUtils.BinaryTreeTraverseTypeEnum.BFS));
        System.out.println("Z字形层次遍历=" + BinaryTreeUtils.traverseBinaryTree(binaryTreeNode, BinaryTreeUtils.BinaryTreeTraverseTypeEnum.ZigZag_trace));
        System.out.println("二叉树的深度为=" + BinaryTreeUtils.getBinaryTreeDeep(binaryTreeNode));
        System.out.println("二叉树中和为K的所有路径为=" + BinaryTreeUtils.getRoot2LeafPathWithSumEqK(43, binaryTreeNode));


    }


}
