package com.ahu.coding.beast.entity;

import lombok.Data;

/**
 * @author jianzhang
 * 2025/01/14/下午4:47
 */
@Data
public class ListNode {
    private Integer value;
    private ListNode next;

    public ListNode(int x) {
        this.value = x;
    }

    public ListNode next(ListNode nextNode){
        this.next = nextNode;
        return nextNode;
    }


    public static void print(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        while (head != null) {
            if (head.getNext() == null) {
                stringBuilder.append(head.getValue());
            } else {
                stringBuilder.append(head.getValue()).append("->");
            }
            head = head.getNext();
        }
        System.out.println(stringBuilder);
    }

}
