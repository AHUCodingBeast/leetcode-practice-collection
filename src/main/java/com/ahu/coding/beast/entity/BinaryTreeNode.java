package com.ahu.coding.beast.entity;

import lombok.Data;

/**
 * @author jianzhang
 * 2025/01/26/上午11:51
 */
@Data
public class BinaryTreeNode {
    private Integer value;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }
}
