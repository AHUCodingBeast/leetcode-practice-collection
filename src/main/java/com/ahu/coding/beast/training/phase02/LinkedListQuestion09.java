package com.ahu.coding.beast.training.phase02;

import com.ahu.coding.beast.entity.ListNode;

import java.util.Objects;

/**
 * @author jianzhang
 * 2025/01/27/下午4:19
 * <p>
 * 给定一个 已排序 的链表的头 head, 删除链表里所有重复的元素, 使每个元素只出现一次.返回已排序的链表。
 */
public class LinkedListQuestion09 {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(10);
        head1.appendNext(new ListNode(15))
                .appendNext(new ListNode(15))
                .appendNext(new ListNode(15))
                .appendNext(new ListNode(35))
                .appendNext(new ListNode(35));
        ListNode.print(removeRepeatNode(head1));

    }

    public static ListNode removeRepeatNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode cur = head;
        while (cur.getNext() != null) {
            ListNode next = cur.getNext();
            if (!next.getValue().equals(cur.getValue())) {
                slow.setNext(cur.getNext());
                slow = slow.getNext();
            }
            cur = cur.getNext();
        }
        if (Objects.equals(slow.getValue(), cur.getValue())) {
            slow.setNext(null);
        }
        return head;
    }

    /**
     * 参考这个
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/solutions/680357/shan-chu-pai-xu-lian-biao-zhong-de-zhong-49v5/">...</a>
     *
     * @param head 头指针
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.getNext() != null) {
            if (Objects.equals(cur.getValue(), cur.getNext().getValue())) {
                cur.setNext(cur.getNext().getNext());
            } else {
                cur = cur.getNext();
            }
        }

        return head;
    }

}
