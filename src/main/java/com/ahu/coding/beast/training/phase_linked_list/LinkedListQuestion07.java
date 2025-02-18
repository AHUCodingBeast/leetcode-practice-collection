package com.ahu.coding.beast.training.phase_linked_list;

import com.ahu.coding.beast.entity.ListNode;

/**
 * @author jianzhang
 * 2025/01/27/上午11:13
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class LinkedListQuestion07 {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.appendNext(new ListNode(2))
                .appendNext(new ListNode(3))
                .appendNext(new ListNode(4))
                .appendNext(new ListNode(5))
                .appendNext(new ListNode(6));
        ListNode h = removeNthFromEnd(head1, 3);
        System.out.println(h.getNodeListStr());
    }

    /**
     * 朴素的两种思路：
     * 1、算出链表长度L 倒数第n个就是正数的第L-n +1  之后的节点 就是我们要删除的节点
     * 2、使用栈  Deque
     * 3、双指针算法:
     * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，并且 first 比 second 超前 n 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
     *
     * @param head
     * @param n
     * @return 新的头结点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        // 快指针先走n步
        for (int i = 0; i < n; ++i) {
            first = first.getNext();
        }
        while (first != null) {
            first = first.getNext();
            second = second.getNext();
        }
        second.setNext(second.getNext().getNext());
        ListNode ans = dummy.getNext();
        return ans;
    }



}
