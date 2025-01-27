package com.ahu.coding.beast.training.phase02;

import com.ahu.coding.beast.entity.ListNode;

/**
 * @author jianzhang
 * 2025/01/24/下午4:03
 * <p>
 * 题目1：判断链表是否有环 {@link LinkedListQuestion05#hasCycle(ListNode)}
 * 题目2：找出链表有环的情况下第一个元素 {@link LinkedListQuestion05#detectCycle(ListNode)}
 */
public class LinkedListQuestion05 {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.appendNext(new ListNode(4))
                .appendNext(new ListNode(5)).appendNext(head1);

        ListNode node = detectCycle(head1);
        System.out.println(node == null ? null : node.getValue());

        System.out.println();

    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        boolean hasCycle = false;
        while (slow != null && fast != null) {
            slow = slow.getNext();
            if (fast.getNext() != null) {
                // 快慢指针
                fast = fast.getNext().getNext();
            } else {
                break;
            }
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        return hasCycle;

    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode ptr = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            // 快慢指针
            fast = fast.getNext().getNext();
            if (slow == fast) {
                // 一定有环 快慢指针相遇的时候，再安排一个指针从头开始走，并且步频一致下次相遇就是入圈节点
                while (slow != ptr) {
                    slow = slow.getNext();
                    ptr = ptr.getNext();
                }
                return ptr;

                // 当时犯下的一个错误 这样设计循环是无法输出结果的，原因在于while(true)的循环至少会执行一次 而使用 while (slow != ptr)  则可能一次都不会执行
//                while (true) {
//                    slow = slow.getNext();
//                    ptr = ptr.getNext();
//                    if (slow == ptr) {
//                        return ptr;
//                    }
//                }

            }
        }
        return null;
    }

}
