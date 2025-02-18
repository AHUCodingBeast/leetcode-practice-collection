package com.ahu.coding.beast.training.phase_linked_list;

import com.ahu.coding.beast.entity.ListNode;

/**
 * @author jianzhang
 * 2025/01/24/下午4:03
 * <p>
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class LinkedListQuestion03 {
    public static void main(String[] args) {
        // head = [1,4,3,2,5,2], x = 3
        ListNode head1 = new ListNode(1);
        head1.appendNext(new ListNode(4))
                .appendNext(new ListNode(3))
                .appendNext(new ListNode(2))
                .appendNext(new ListNode(5))
                .appendNext(new ListNode(2));
        ListNode.print(head1);

        ListNode partition = partition(head1, 3);
        ListNode.print(partition);

    }

    public static ListNode partition(ListNode head, int x) {

        // 定义了两个新的链表 一个记录比x小的一个记录比x大的，最后串起来
        // 使用dummyHead是链表的常见操作
        ListNode h1 = new ListNode(-1);
        ListNode h2 = new ListNode(-1);
        ListNode cur = head;
        ListNode p1 = h1;
        ListNode p2 = h2;

        while (cur != null) {
            if (cur.getValue() < x) {
                p1.setNext(cur);
                cur = cur.getNext();
                p1 = p1.getNext();
                p1.setNext(null);
            } else {
                p2.setNext(cur);
                cur = cur.getNext();
                p2 = p2.getNext();
                p2.setNext(null);
            }
        }

        if (p1 == h1) {
            return h2.getNext();
        }
        if (p2 == h2) {
            return h1.getNext();
        }
        p1.setNext(h2.getNext());
        return h1.getNext();
    }

}
