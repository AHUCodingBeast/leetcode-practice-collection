package com.ahu.coding.beast.training.phase02;

import com.ahu.coding.beast.entity.ListNode;

/**
 * @author jianzhang
 * 2025/01/24/下午4:03
 * <p>
 * 优雅的获取链表的中间结点
 */
public class LinkedListQuestion08 {
    public static void main(String[] args) {

    }

    /**
     * 获取链表的中间结点 （也可以使用转为数组来搞 也是一种思路）
     *
     * @param head 头指针
     * @return 中间结点
     */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

}
