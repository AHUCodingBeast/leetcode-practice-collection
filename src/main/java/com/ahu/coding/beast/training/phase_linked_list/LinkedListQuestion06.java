package com.ahu.coding.beast.training.phase_linked_list;

import com.ahu.coding.beast.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jianzhang
 * 2025/01/24/下午4:03
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */
public class LinkedListQuestion06 {
    public static void main(String[] args) {

    }


    /**
     * 对于这道题我一开始想的方案是，对齐两个链表，分别计算两个链表的长度，然后让长的指针先移动m个步长，然后再开始逐个比较遇到第一个相等的就说明是相交节点。这个思路可行 但是不够优雅。下面的思路则比较巧妙
     * <p>
     * 官方题解
     * 当链表 headA 和 headB 都不为空时，创建两个指针 pA 和 pB，初始时分别指向两个链表的头节点 headA 和 headB，然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
     * 每步操作需要同时更新指针 pA 和 pB。
     * 如果指针 pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。
     * 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表 headA 的头节点。
     * 当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null。
     * <p>
     * 下面提供双指针方法的正确性证明。考虑两种情况，第一种情况是两个链表相交，第二种情况是两个链表不相交。
     * 情况一：两个链表相交
     * 链表 headA 和 headB 的长度分别是 m 和 n。假设链表 headA 的不相交部分有 a 个节点，链表 headB 的不相交部分有 b 个节点，两个链表相交的部分有 c 个节点，则有 a+c=m，b+c=n。
     * 如果 a=b，则两个指针会同时到达两个链表相交的节点，此时返回相交的节点；
     * 如果 a!=b，则指针 pA 会遍历完链表 headA，指针 pB 会遍历完链表 headB，两个指针不会同时到达链表的尾节点，然后指针 pA 移到链表 headB 的头节点，指针 pB 移到链表 headA 的头节点，然后两个指针继续移动，在指针 pA 移动了 a+c+b 次、指针 pB 移动了 b+c+a 次之后，两个指针会同时到达两个链表相交的节点，该节点也是两个指针第一次同时指向的节点，此时返回相交的节点。
     * <p>
     * 情况二：两个链表不相交
     * 链表 headA 和 headB 的长度分别是 m 和 n。考虑当 m=n 和 m=n 时，两个指针分别会如何移动：
     * 如果 m=n，则两个指针会同时到达两个链表的尾节点，然后同时变成空值 null，此时返回 null；
     * 如果 m!=n，则由于两个链表没有公共节点，两个指针也不会同时到达两个链表的尾节点，因此两个指针都会遍历完两个链表，在指针 pA 移动了 m+n 次、指针 pB 移动了 n+m 次之后，两个指针会同时变成空值 null，此时返回 null。
     * 。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.getNext();
            pB = pB == null ? headA : pB.getNext();
        }
        return pA;
    }


    /**
     * 首先遍历链表 headA，并将链表 headA 中的每个节点加入哈希集合中。然后遍历链表 headB，对于遍历到的每个节点，判断该节点是否在哈希集合中：
     * 如果当前节点不在哈希集合中，则继续遍历下一个节点；
     * 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都在两个链表的相交部分，因此在链表 headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，返回该节点。
     * 如果链表 headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回 null。
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 相交节点
     */
    public ListNode getIntersectionNodeUsingHashSet(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.getNext();
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }


    public ListNode getIntersectionNodeStupid(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }
        int len1 = 1;
        int len2 = 1;

        ListNode t1 = headA;
        ListNode t2 = headB;
        while (t1 != null) {
            t1 = t1.getNext();
            len1++;
        }
        while (t2 != null) {
            t2 = t2.getNext();
            len2++;
        }

        int gap = len1 > len2 ? len1 - len2 : len2 - len1;
        ListNode p1 = headA;
        ListNode p2 = headB;
        if (len1 > len2) {
            while (gap > 0) {
                p1 = p1.getNext();
                gap--;
            }
        }
        if (len1 <= len2) {
            while (gap > 0) {
                p2 = p2.getNext();
                gap--;
            }
        }

        while (p1 != p2 && p1 != null && p2 != null) {
            p1 = p1.getNext();
            p2 = p2.getNext();
            if (p1 == p2) {
                return p1;
            }
        }
        return null;

    }


}
