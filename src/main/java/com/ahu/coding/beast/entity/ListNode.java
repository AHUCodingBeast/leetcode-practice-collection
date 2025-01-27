package com.ahu.coding.beast.entity;

import lombok.Data;

import java.util.Objects;

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

    public ListNode(Integer value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode appendNext(ListNode nextNode){
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

    @Override
    public String toString() {
        return "ListNode{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListNode listNode)) {
            return false;
        }
        return Objects.equals(getValue(), listNode.getValue()) && Objects.equals(getNext(), listNode.getNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getNext());
    }
}
