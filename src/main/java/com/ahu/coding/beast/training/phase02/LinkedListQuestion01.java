package com.ahu.coding.beast.training.phase02;

import com.ahu.coding.beast.entity.ListNode;

/**
 * @author jianzhang
 * 2025/01/24/下午3:28
 * 合并两个有序链表  递归写法+循环写法
 */
public class LinkedListQuestion01 {

    public static void main(String[] args) {

        ListNode head1 = new ListNode(10);
        head1.appendNext(new ListNode(15)).appendNext(new ListNode(20)).appendNext(new ListNode(35));
        ListNode.print(head1);


        ListNode head2 = new ListNode(13);
        head2.appendNext(new ListNode(14)).appendNext(new ListNode(17)).appendNext(new ListNode(25));
        ListNode.print(head2);

        //     ListNode.print(mergeTwoOrderedSeqList(head1, head2));
        ListNode.print(mergeTwoOrderedSeqListByRecursive(head1, head2));
    }


    public static ListNode mergeTwoOrderedSeqListByRecursive(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }

        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        ListNode head = null;
        if (head1.getValue() < head2.getValue()) {
            head = head1;
            head.setNext(mergeTwoOrderedSeqListByRecursive(head1.getNext(), head2));
        } else {
            head = head2;
            head.setNext(mergeTwoOrderedSeqListByRecursive(head1, head2.getNext()));
        }


        return head;
    }


    public static ListNode mergeTwoOrderedSeqList(ListNode head1, ListNode head2) {

        if (head1 == null && head2 == null) {
            return null;
        }

        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }


        ListNode newHead = new ListNode(0);

        ListNode cur1 = head1;
        ListNode cur2 = head2;
        ListNode cur3 = newHead;


        while (cur1 != null && cur2 != null) {
            if (cur1.getValue() < cur2.getValue()) {
                cur3.setNext(cur1);
                cur1 = cur1.getNext();
                cur3 = cur3.getNext();
            } else {
                cur3.setNext(cur2);
                cur2 = cur2.getNext();
                cur3 = cur3.getNext();
            }
        }
        while (cur1 != null) {
            cur3.setNext(cur1);
            cur3 = cur3.getNext();
            cur1 = cur1.getNext();
        }

        while (cur2 != null) {
            cur3.setNext(cur2);
            cur3 = cur3.getNext();
            cur2 = cur2.getNext();
        }

        return newHead.getNext();
    }
}
