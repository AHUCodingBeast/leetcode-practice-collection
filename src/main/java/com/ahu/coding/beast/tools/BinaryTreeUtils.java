package com.ahu.coding.beast.tools;

import com.ahu.coding.beast.entity.BinaryTreeNode;
import com.ahu.coding.beast.tools.binaryTree.BinaryTreeInfo;
import com.ahu.coding.beast.tools.binaryTree.BinaryTrees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jianzhang
 * 2025/01/26/下午1:55
 */
public class BinaryTreeUtils {


    /**
     * 美观的打印一棵二叉树
     *
     * @param root 根节点
     */
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
     * 遍历二叉树 【BFS 、DFS（先序、中序、后序遍历）】
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
            case ZigZag_trace -> {
                zigZagBfsTraverseBinaryTree(root, res);
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
     * 求解二叉树从根到叶子节点的路径，该路径满足路径上的所有节点之和等于给定数值K
     *
     * @param k    目标值
     * @param root 根节点
     * @return 所有路径
     */
    public static List<List<Integer>> getRoot2LeafPathWithSumEqK(int k, BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        getRoot2LeafPathWithSumEqK(k, root, result, new ArrayList<>());
        return result;
    }


    private static void getRoot2LeafPathWithSumEqK(int k, BinaryTreeNode node, List<List<Integer>> result, List<Integer> path) {

        if (node == null) {
            return;
        }

        int remain = k - node.getValue();
        path.addLast(node.getValue());
        // 确保遍历到了叶子节点并且余量已经为0了
        if (node.getRight() == null && node.getLeft() == null && remain == 0) {
            result.add(new ArrayList<>(path));
        }

        getRoot2LeafPathWithSumEqK(remain, node.getLeft(), result, path);
        getRoot2LeafPathWithSumEqK(remain, node.getRight(), result, path);
        path.removeLast();

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


    private static void zigZagBfsTraverseBinaryTree(BinaryTreeNode root, StringBuilder trace) {

        LinkedList<BinaryTreeNodeExt> queue = new LinkedList<>();
        queue.addLast(new BinaryTreeNodeExt(1, root));

        int maxDeep = 1;
        Map<Integer, List<Integer>> map = new HashMap<>();

        while (!queue.isEmpty()) {
            BinaryTreeNodeExt nodeExt = queue.removeFirst();
            BinaryTreeNode node = nodeExt.getNode();
            int deep = nodeExt.getDeep();
            if (deep > maxDeep) {
                maxDeep = deep;
            }


            boolean fromLeftToRight = deep % 2 == 1;
            map.computeIfAbsent(deep, k -> new ArrayList<>());
            if (fromLeftToRight) {
                map.get(deep).addLast(node.getValue());
            } else {
                map.get(deep).addFirst(node.getValue());
            }

            if (node.getLeft() != null) {
                queue.addLast(new BinaryTreeNodeExt(deep + 1, node.getLeft()));
            }

            if (node.getRight() != null) {
                queue.addLast(new BinaryTreeNodeExt(deep + 1, node.getRight()));
            }

        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= maxDeep; i++) {
            result.addAll(map.get(i));
        }
        trace.append(result.stream().map(String::valueOf).collect(Collectors.joining(",")));
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
        LAST_ORDER("后续遍历"),
        ZigZag_trace("Z字型层次遍历");


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


