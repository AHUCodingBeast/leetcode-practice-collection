package com.ahu.coding.beast.training.phase02;

import com.ahu.coding.beast.entity.ListNode;

import static com.ahu.coding.beast.entity.ListNode.print;

/**
 * @author jianzhang
 * 2025/01/14/下午4:48
 * 反转链表 要求写出两种方法
 * 2->4->3->1  反转之后为 1->3->4->2
 */
public class LinkedListQuestion02 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(1);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);

        print(listNode1);
        print(reverseListNodeByIteration(listNode1));

    }




    /**
     * 迭代法翻转链表
     *
     * @param head 链表头指针
     * @return 翻转之后的链表头指针
     */
    public static ListNode reverseListNodeByIteration(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        while (head != null) {
            // 看不明白这个代码一定画个图看看分析下
            ListNode next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }


    /**
     * 递归法 翻转链表
     * @param head  链表头指针
     * @return 翻转之后的链表头指针
     */
    public static ListNode reverseListNodeByRecursion(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode newHead = reverseListNodeByRecursion(head.getNext());
        // 2->4->3->1  变成了这样  2   1->3->4   2 此时的下一个节点是4，所以需要让4 的下一个节点是2 才行
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }


}
