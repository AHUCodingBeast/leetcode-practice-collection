package com.ahu.coding.beast.tools;

import com.ahu.coding.beast.entity.BinaryTreeNode;
import com.ahu.coding.beast.tools.binaryTree.BinaryTreeInfo;
import com.ahu.coding.beast.tools.binaryTree.BinaryTrees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author jianzhang
 * 2025/01/26/下午1:55
 */
public class BinaryTreeUtils {


    public static void prettyFormatTree(BinaryTreeNode root) {
        BinaryTreeInfo treeInfo = new BinaryTreeInfo() {
            @Override
            public Object root() {
                return root;
            }

            @Override
            public Object left(Object node) {
                return ((BinaryTreeNode) node).getLeft();
            }

            @Override
            public Object right(Object node) {
                return ((BinaryTreeNode) node).getRight();
            }

            @Override
            public Object string(Object node) {
                return ((BinaryTreeNode) node).getValue();
            }
        };
        System.out.println(BinaryTrees.printString(treeInfo));
        ;
    }

    /**
     * 基于数组构造平衡二叉树 （任意节点的左右子树的高度差不超过1）
     *
     * @param nums 数元素
     * @param low  低位index
     * @param high 高位index
     * @return root 节点
     */
    public static BinaryTreeNode buildBalanceBinaryTree(int[] nums, int low, int high) {

        if (nums == null || low > high) {
            return null;
        }

        if (low == high) {
            return new BinaryTreeNode(nums[low]);
        }

        int mid = (low + high) / 2;

        BinaryTreeNode root = new BinaryTreeNode(nums[mid]);
        BinaryTreeNode left = buildBalanceBinaryTree(nums, low, mid - 1);
        BinaryTreeNode right = buildBalanceBinaryTree(nums, mid + 1, high);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }


    /**
     * 遍历二叉树
     *
     * @param root             根节点
     * @param traverseTypeEnum 遍历方式
     * @return 遍历结果
     */
    public static String traverseBinaryTree(BinaryTreeNode root, BinaryTreeTraverseTypeEnum traverseTypeEnum) {
        if (root == null) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        switch (traverseTypeEnum) {
            case BFS -> {
                bfsTraverseBinaryTree(root, res);
                return res.toString();
            }
            case MID_ORDER -> {
                midTraverseBinaryTree(root, res);
                return res.toString();
            }
            case PRE_ORDER -> {
                preTraverseBinaryTree(root, res);
                return res.toString();
            }
            case LAST_ORDER -> {
                lastTraverseBinaryTree(root, res);
                return res.toString();
            }
            default -> {
                throw new UnsupportedOperationException("TraverseTypeEnum Unsupported");
            }
        }
    }


    /**
     * 获取二叉树的深度
     *
     * @param root 根节点
     * @return 深度值
     */
    public static int getBinaryTreeDeep(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLength = getBinaryTreeDeep(root.getLeft());
        int rightLength = getBinaryTreeDeep(root.getRight());
        return Math.max(leftLength, rightLength) + 1;
    }

    /**
     * 利用BFS层次遍历求解二叉树的深度
     *
     * @param root 根节点
     * @return 深度
     */
    public static int getBinaryTreeDeepNoRecursive(BinaryTreeNode root) {
        Queue<BinaryTreeNodeExt> queue = new LinkedList<>();
        queue.add(new BinaryTreeNodeExt(1, root));

        int maxDeep = -1;

        while (!queue.isEmpty()) {
            BinaryTreeNodeExt poll = queue.poll();
            BinaryTreeNode rightNode = poll.getNode().getRight();
            BinaryTreeNode leftNode = poll.getNode().getLeft();
            if (leftNode != null) {
                queue.add(new BinaryTreeNodeExt(poll.getDeep() + 1, leftNode));
            }
            if (rightNode != null) {
                queue.add(new BinaryTreeNodeExt(poll.getDeep() + 1, rightNode));
            }
            maxDeep = Math.max(poll.getDeep(), maxDeep);
        }
        return maxDeep;
    }


    //---------------------------私有方法-------------------------

    /**
     * 层次遍历（最简单写法）
     * 如果需要记录每个节点的深度可以参考：
     * {@link com.ahu.coding.beast.tools.BinaryTreeUtils#getBinaryTreeDeepNoRecursive(com.ahu.coding.beast.entity.BinaryTreeNode)}
     *
     * @param root  根节点
     * @param trace 遍历记录
     */
    private static void bfsTraverseBinaryTree(BinaryTreeNode root, StringBuilder trace) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();
            trace.append(poll.getValue()).append("\t");
            if (poll.getLeft() != null) {
                queue.add(poll.getLeft());
            }
            if (poll.getRight() != null) {
                queue.add(poll.getRight());
            }
        }
    }


    private static void midTraverseBinaryTree(BinaryTreeNode root, StringBuilder trace) {
        if (root == null) {
            return;
        }
        midTraverseBinaryTree(root.getLeft(), trace);
        trace.append(root.getValue()).append("\t");
        midTraverseBinaryTree(root.getRight(), trace);
    }

    private static void preTraverseBinaryTree(BinaryTreeNode root, StringBuilder trace) {
        if (root == null) {
            return;
        }
        trace.append(root.getValue()).append("\t");
        preTraverseBinaryTree(root.getLeft(), trace);
        preTraverseBinaryTree(root.getRight(), trace);
    }


    private static void lastTraverseBinaryTree(BinaryTreeNode root, StringBuilder trace) {
        if (root == null) {
            return;
        }
        lastTraverseBinaryTree(root.getLeft(), trace);
        lastTraverseBinaryTree(root.getRight(), trace);
        trace.append(root.getValue()).append("\t");
    }


    @Getter
    public enum BinaryTreeTraverseTypeEnum {
        BFS("层次遍历"),
        PRE_ORDER("先序遍历"),
        MID_ORDER("中序遍历"),
        LAST_ORDER("后续遍历");


        private final String name;

        BinaryTreeTraverseTypeEnum(String name) {
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class BinaryTreeNodeExt {

        private int deep;
        private BinaryTreeNode node;

    }

}


