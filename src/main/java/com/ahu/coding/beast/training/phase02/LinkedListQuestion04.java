package com.ahu.coding.beast.training.phase02;

import com.ahu.coding.beast.entity.ListNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import static com.ahu.coding.beast.training.phase02.LinkedListQuestion01.mergeTwoOrderedSeqList;

/**
 * @author jianzhang
 * 2025/01/24/下午4:03
 * 题目：合并 K 个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
public class LinkedListQuestion04 {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next(new ListNode(4))
                .next(new ListNode(5));
        ListNode head2 = new ListNode(1);
        head2.next(new ListNode(3))
                .next(new ListNode(4));
        ListNode head3 = new ListNode(2);
        head3.next(new ListNode(6));

        ListNode[] data = new ListNode[]{head1, head2, head3};


//        ListNode listNode = mergeKLists(data);
//        ListNode.print(listNode);

//        ListNode listNode = mergeKListsByMerge(data, 0, data.length - 1);
//        ListNode.print(listNode);

        ListNode listNode1 = mergeKListsByPriorityQueue(data);
        ListNode.print(listNode1);


    }


    /**
     * 解决方案1  退化为合并两个有序链表，纯暴力解法
     *
     * @param lists 链表数组
     * @return 合并后的头指针
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        int i = 0;
        ListNode t1 = lists[i];
        while (i < lists.length - 1) {
            t1 = mergeTwoOrderedSeqList(t1, lists[i + 1]);
            i++;
        }
        return t1;
    }


    /**
     * 解决方案2 分治算法  典型的二叉树模板 类似归并排序的写法
     *
     * @param lists 链表数组
     * @return 合并后的头指针
     * @see <a href="https://blog.csdn.net/K346K346/article/details/135679949">参考这里</a>
     */
    public static ListNode mergeKListsByMerge(ListNode[] lists, int low, int high) {
        if (low >= high) {
            return lists[low];
        }
        int mid = (high + low) / 2;
        ListNode left = mergeKListsByMerge(lists, low, mid);
        ListNode right = mergeKListsByMerge(lists, mid + 1, high);
        // 调用合并两个有序链表的方法
        return mergeTwoOrderedSeqList(left, right);
    }


    /**
     * 解决方案3 借助一个优先级队列 详细看代码 外加一个辅助指针
     *
     * @param lists 链表数组
     * @return 合并后的头指针
     * @see <a href="https://blog.csdn.net/K346K346/article/details/135679949">参考这里</a>
     */
    public static ListNode mergeKListsByPriorityQueue(ListNode[] lists) {

        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(ListNode::getValue));
        Collections.addAll(heap, lists);
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;
        while (!heap.isEmpty()) {
            ListNode t = heap.poll();
            current.setNext(t);
            current = t;
            if (current.getNext() != null) {
                heap.add(current.getNext());
            }
        }
        return dummyHead.getNext();
    }


}
