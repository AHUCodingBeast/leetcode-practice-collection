package com.ahu.coding.beast.training.phase_linked_list;

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
        ListNode fast = head;
        // 思路是 快慢指针 快指针发现当前元素和下一个元素不同的话，就把下一个元素加到slow所串接的链表里面
        while (fast.getNext() != null) {
            ListNode next = fast.getNext();
            if (!next.getValue().equals(fast.getValue())) {
                slow.setNext(fast.getNext());
                slow = slow.getNext();
            }
            fast = fast.getNext();
        }
        if (Objects.equals(slow.getValue(), fast.getValue())) {
            slow.setNext(null);
        }
        return head;
    }

    /**
     * 参考这个
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/solutions/680357/shan-chu-pai-xu-lian-biao-zhong-de-zhong-49v5/">...</a>
     * @param head 头指针
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
