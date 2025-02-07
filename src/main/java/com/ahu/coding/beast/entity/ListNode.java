package com.ahu.coding.beast.entity;

import java.util.Objects;

/**
 * @author jianzhang
 * 2025/01/14/下午4:47
 */
public class ListNode {
    public Integer value;
    public ListNode next;

    public ListNode(int x) {
        this.value = x;
    }

    public ListNode(Integer value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public static void print(ListNode head) {

        ListNode p = head;

        StringBuilder stringBuilder = new StringBuilder();
        while (p != null) {
            if (p.getNext() == null) {
                stringBuilder.append(p.getValue());
            } else {
                stringBuilder.append(p.getValue()).append("->");
            }
            p = p.getNext();
        }
        System.out.println(stringBuilder);
    }

    public ListNode appendNext(ListNode nextNode) {
        this.next = nextNode;
        return nextNode;
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


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
