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
        ListNode head1 = new ListNode(1);
        head1.appendNext(new ListNode(2))
                .appendNext(new ListNode(3))
                .appendNext(new ListNode(4))
                .appendNext(new ListNode(5))
                .appendNext(new ListNode(6));

        ListNode middle = middleNode(head1);
        System.out.println(middle == null ? null : middle.getValue());
    }

    /**
     * 获取链表的中间结点 （也可以使用转为数组来搞 也是一种思路）
     * 【快指针走两步，慢指针走一步，快指针走到头的时候慢指针的位置就是中间结点】
     *
     * @param head 头指针
     * @return 中间结点
     */
    public static ListNode middleNode(ListNode head) {
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
