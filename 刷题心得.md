## 前言

首先这里先注意下SortAlgorithm里面的代码，这里面写了五种排序的写法，不要小看排序，知道原理到代码落地其实是不同的，算法训练如果不实际去写代码，纸上谈兵是万万不行的。
排序算法中难度最大的是归并排序（`com.ahu.coding.beast.tools.SortAlgorithmUtils.mergeSort`
）的写法，这个排序分治和递归的思想同时运用了，实在不太好写，第一次写的时候怎么都写不对代码，此外在快速排序的案例中选择最后一个作为‘基准’会更容易写代码，反之如果每次都选中间的则很难写。

其次在第一阶段的问题解决中，写了几道动态规划+贪心算法 的经典题型例如 ：

- 换零钱问题（`Question09` `Question10`）
- 最长公共子串（`com.ahu.coding.beast.training.phase01.Question04.getCommonSubStringLength`）
- 最长公共子序列（`com.ahu.coding.beast.training.phase01.Question07.longestCommonSequence`）  
  对于最长公共子串和最长公共子序列一定要反复的回顾 真的是非常经典

此外第一阶段的联系中还涉及了几道对数字的处理，对于一个整数来说，对整数反复对10取余，然后对10做除法，就能依次的从个位开始分离每一位数字
参考（`Question05`）

第一阶段的刷题主题比较混乱，从第二阶段开始将按照下面的顺序（[刷题题集](https://labuladong.online/algo/intro/quick-learning-plan/#%E5%85%B3%E4%BA%8E%E9%A2%98%E5%8D%95)
）进行分类刷题，并将每个分类下面的做题心得记录到下面对应的分类里面

## 递归

对于一个可以用递归方法解决的问题，需要按照以下三个步骤进行分析

1. 考虑最简单的场景应该怎么解决问题
2. 定义好解决问题的函数定义，例如典型的阶乘问题， 先定义好函数的入参还有出参到底是什么
3. 定义好递归的出口
4. 考虑通过调用第二步的函数来一步步拆解问题

在大量的递归案例中我们可以总结下述规律：
只要是递归形式的遍历，都可以有前序位置和后序位置，分别在递归之前和递归之后，前序位置就可以理解为是进入一个递归节点之前做的事情，后序位置则是离开一个递归节点做的事情。如下面的代码所示：

```java
    public static void traverseListNode(ListNode head) {
    if (head == null) {
        return;
    }
    // System.out.print(head.getValue() + " "); 前序位置  写在这里就是顺序打印
    traverseListNode(head.getNext());
    // 后序位置，写在这里就是逆序打印
    System.out.print(head.getValue() + " ");
}
```

有些情况比较复杂一点，在一个方法中调用多次递归，例如下面的合并K个有序链表的问题，转为递归思路就是先合并low->mid 的链表，
再合并mid+1 -> high 的链表，最后将这两个链表合并，这个则是典型的二叉树遍历模型，相当于是后序遍历，也就是说复杂一点的递归设计，我们可以考虑可不可以把问题转为二叉树的遍历问题去解决

```java
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
```

## 回溯算法

回溯算法的本质是从二叉树或者多叉树种无数条到叶子节点的路径中，选择一条符合题目要求的路径，基本框架如下所示，典型的案例就是 [全排列]`com.ahu.coding.beast.training.phase01.Question11`

```python
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    // 关键在这里 ↓
    for 选择 in 选择列表:
        做选择(进入节点前)
        backtrack(路径, 选择列表)
        撤销选择
```

## 分治

分治可以说是一种基本思想例如下面的几个案例：

- 快速排序：就是先处理pivot的位置，pivot位置处理好了分别取递归处理左边和右边的数组，相当于二叉树的前序遍历。
 （代码位置：`com.ahu.coding.beast.tools.SortAlgorithmUtils.quickSortMethod`）
- 归并排序：则是相当于我们先对 nums[lo..mid] 排序，再对 nums[mid+1..hi] 排序，最后把这两个有序的子数组合并，整个数组就排好序了，这个相当于二叉树的后序遍历
  （代码位置：`com.ahu.coding.beast.tools.SortAlgorithmUtils.mergeSort`）
- K个升序链表合并:我们先合并low->mid 的所有链表，再合并mid-> high 的所有链表，最后把两个最终的链表合并，k个链表也就合并完毕了，和上面一样也是后序遍历。
  （代码位置：`com.ahu.coding.beast.training.phase02.LinkedListQuestion04.mergeKListsByMerge`）
  可以发现共同点是：
- 都需要分解问题（例如将数组拆成左边和右边）
- 要递归的去解决子问题
- 将子问题的解进行合并，得到原问题的解

常用的分解问题的套路有：二分分解、首部分解

## 双指针

### 数组双指针问题

左右指针法，典型例题：

- `com.ahu.coding.beast.training.phase01.Question14`, `com.ahu.coding.beast.training.phase01.Question01.twoSum3`
  一般套路是一左一右两个指针逐步向中间靠拢，在靠拢的过程中找到一个可行的解
- `com.ahu.coding.beast.training.phase01.Question04.solution03` 则是中心向两边发散，发散的过程中进行问题求解

快慢指针法，典型例题：

- `com.ahu.coding.beast.training.phase01.Question13`,`com.ahu.coding.beast.training.phase01.Question12`,`com.ahu.coding.beast.training.phase02.LinkedListQuestion09`
  一般套路都是快指针来进行条件判断，完成一个数组的遍历，然后慢指针所指的位置就是需要和快指针进行数据操作的位置，比如进行互换或者覆盖等

### 链表双指针问题

- `com.ahu.coding.beast.training.phase02.LinkedListQuestion06.getIntersectionNode`
  找出两个链表相交的节点，使用了双指针，遍历到头之后再转到另一个链表的头部开始遍历，两指针相遇的时候就是相交的节点，从而无需做链表对齐
- `com.ahu.coding.beast.training.phase02.LinkedListQuestion05.detectCycle`
  通过一个快指针和一个慢指针，来判断是否有环，并且通过数学推导，在快慢指针相遇之后，再安排一个指针从头遍历到新指针和慢指针相遇的时候就是入环的第一个节点，非常巧妙
- `com.ahu.coding.beast.training.phase02.LinkedListQuestion07.removeNthFromEnd` 通过让快指针先走N步，从而快速的找到倒数的第N个元素的位置，而不用求解链表长度
- `com.ahu.coding.beast.training.phase02.LinkedListQuestion08.middleNode` 通过让快指针每次走两步，慢指针每次走一步的方式快速的找出链表的中间结点

### 特殊的双指针：滑动窗口

- `com.ahu.coding.beast.training.phase01.Question03.getLongestSubStringBySlidingWindow`
  在这个代码里面使用了一个Set作为窗口，用来记录最长的不重复子串的具体字符集。

## 单调栈

## 数组

## 递归程序设计

## 动态规划



