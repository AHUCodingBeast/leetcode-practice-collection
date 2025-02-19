## 排序

排序的代码逻辑全部集中在 SortAlgorithm里面，这里面写了五种排序的写法，不要小看排序，知道原理到代码落地其实是不同的，算法训练如果不实际去写代码，纸上谈兵是万万不行的。
排序算法中难度最大的是归并排序（`SortAlgorithmUtils.mergeSort`
）的写法，这个排序分治和递归的思想同时运用了，实在不太好写，第一次写的时候怎么都写不对代码，此外在快速排序的案例中选择最后一个作为‘基准’会更容易写代码，反之如果每次都选中间的则很难写。

## 二叉树（重要）
重点查看 `com.ahu.coding.beast.tools.BinaryTreeUtils` 中的代码内容，涵盖了所有遍历，这是我们培养递归程序写法思路的基本。


## 递归
编写递归算法，有两种思维模式：一种是通过「遍历」一遍树得到答案，另一种是通过「分解问题」

先说一下分解问题的思维模式，典型的比如求解斐波拉切数列的问题，先把N分解为N-1和N-2的问题，然后直接分解到0和1的基本case，再比如二叉树的深度计算（`com.ahu.coding.beast.tools.BinaryTreeUtils.getBinaryTreeDeep`）想计算整棵树的最大深度，可以先计算左右子树的最大深度，取两者的最大值加一，就是整棵树的最大深度。 那么我们可以给 maxDepth 函数一个明确的定义：输入一棵二叉树的节点，函数返回以这个节点为根的二叉树的最大深度。
分解问题的思维模式大致有下面的几个步骤：  
1. 考虑最简单的场景应该怎么解决问题
2. 定义好解决问题的函数定义，例如典型的阶乘问题， 先定义好函数的入参还有出参到底是什么
3. 定义好递归的出口
4. 考虑通过调用第二步的函数来一步步拆解问题

再说一下遍历的思维模式，所谓遍历的思维模式就是考虑所有解的可能性，是不是相当于遍历一棵树，比如典型的全排列问题（`Question11.getAllArrangement`）。
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

参考文章：[回溯算法](https://labuladong.online/algo/essential-technique/permutation-combination-subset-all-in-one/#%E5%AD%90%E9%9B%86-%E5%85%83%E7%B4%A0%E6%97%A0%E9%87%8D%E4%B8%8D%E5%8F%AF%E5%A4%8D%E9%80%89) 

回溯算法的本质是从二叉树或者多叉树种无数条到叶子节点的路径中，选择一条符合题目要求的路径，基本框架如下所示，典型的案例就是 [全排列]`Question11`

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
回溯算法有一个超级大类就是排列组合问题  

###  组合（子集）问题树    

我们要求解[1,2,3] 的所有子集，我们可以在脑海中构建出下面的这一棵树，这棵树的特点是，不能选位于元素num[i]之前的元素，这就是[1,3] 下面不能发散出[1,3,2] 的原因，因为这样就重复了(因为集合具备无序性)

![组合（子集）问题树](https://labuladong.online/algo/images/permutation/5.jpeg)  

子集问题代码： `com.ahu.coding.beast.training.phase_tree.Question30`重点关键代码如下，是符合回溯问题基本代码框架的

```java
static void doTrace(int[] nums, int startIndex, List<Integer> trace, List<List<Integer>> result) {
    result.add(new ArrayList<>(trace));
    for (int i = startIndex; i < nums.length; i++) {
        // 做选择
        trace.addLast(nums[i]);
        // 执行递归 只能追加位于目标元素之后的元素
        doTrace(nums, i + 1, trace, result);
        // 撤销选择
        trace.removeLast();
    }
}
```

围绕这棵树就可以问出无数变种问题比如类似给你一个数组，请你找出和的值为K 的所有组合，看着上面的图示是不是立马明了，实际上就是挨个考察树的节点的和。  
### 排列问题树  
![排列问题树](https://labuladong.online/algo/images/permutation/7.jpeg)  

相比组合树，排列树有个特点就是，排列是讲究顺序了所以不能认为[1,3,2]和 [1,2,3] 是同一种结果,但排列问题本身就是让你穷举元素的位置，nums[i] 之后也可以出现 nums[i] 左边的元素，所以之前的那一套玩不转了，需要额外使用 used 数组来标记哪些元素还可以被选择。
排列问题代码：`com.ahu.coding.beast.training.phase_tree.Question11` 重点代码如下

```java
public static void trace(List<Integer> nums, boolean[] used, List<Integer> traceList, List<List<Integer>> res) {

    // 路径的长度等于nums的长度的时候代表存在一个可行解了
    if (traceList.size() == nums.size()) {
        res.add(new ArrayList<>(traceList));
    }

    for (int i = 0; i < nums.size(); i++) {
        if (used[i]) {
            continue;
        }
        // 做选择
        used[i] = true;
        traceList.addLast(nums.get(i));

        // 继续遍历树
        trace(nums, used, traceList, res);

        // 撤销选择
        traceList.removeLast();
        used[i] = false;
    }
}
```

PS: 对于这两个树的代码真的要做到能默写的程度。注意的是回溯问题的递归写法，相当于遍历一棵树，遍历问题一般不设计方法的返回，所以都是返回void


上面给的模板回溯代码解决的是标准子集或者标准排列的一类问题，但是实际情况可能还存在一种特殊场景也就是给定的目标数组存在重复数字，例如给定数组为[1,2,2] 这种情况下，直接套用上面的模板代码给出的结果就会存在重复现象
![特殊情况](https://labuladong.online/algo/images/permutation/9.jpeg)

这时就要考虑回溯"剪枝" 详情参考 `Question32`




典型例题：  
`com.ahu.coding.beast.training.phase_tree.Question32`  
`com.ahu.coding.beast.training.phase_tree.Question11`


## 分治

分治可以说是一种基本思想例如下面的几个案例：

- 快速排序：就是先处理pivot的位置，pivot位置处理好了分别取递归处理左边和右边的数组，相当于二叉树的前序遍历。
  （代码位置：`com.ahu.coding.beast.tools.SortAlgorithmUtils.quickSortMethod`）
- 归并排序：则是相当于我们先对 nums[lo..mid] 排序，再对 nums[mid+1..hi] 排序，最后把这两个有序的子数组合并，整个数组就排好序了，这个相当于二叉树的后序遍历
  （代码位置：`com.ahu.coding.beast.tools.SortAlgorithmUtils.mergeSort`）
- K个升序链表合并:我们先合并low->mid 的所有链表，再合并mid-> high 的所有链表，最后把两个最终的链表合并，k个链表也就合并完毕了，和上面一样也是后序遍历。
  （代码位置：`com.ahu.coding.beast.training.phase_linked_list.LinkedListQuestion04.mergeKListsByMerge`）
  可以发现共同点是：
- 都需要分解问题（例如将数组拆成左边和右边）
- 要递归的去解决子问题
- 将子问题的解进行合并，得到原问题的解

常用的分解问题的套路有：二分分解、首部分解

## 双指针

### 数组双指针问题

左右指针法，典型例题：

- `Question14`, `Question01.twoSum3` , `Question08`  
  一般套路是一左一右两个指针逐步向中间靠拢，在靠拢的过程中找到一个可行的解
- `Question04.solution03` 则是中心向两边发散，发散的过程中进行问题求解
- `Question24` 进阶版的左右指针 (三数之和)
- `Question28` 进阶版的左右指针 (四数之和)

快慢指针法，典型例题：

- `Question13`,  `Question12`,  `LinkedListQuestion09`
  一般套路都是快指针来进行条件判断，完成一个数组的遍历，然后慢指针所指的位置就是需要和快指针进行数据操作的位置，比如进行互换或者覆盖等



### 链表双指针问题

- `LinkedListQuestion06.getIntersectionNode`
  找出两个链表相交的节点，使用了双指针，遍历到头之后再转到另一个链表的头部开始遍历，两指针相遇的时候就是相交的节点，从而无需做链表对齐
- `LinkedListQuestion05.detectCycle`
  通过一个快指针和一个慢指针，来判断是否有环，并且通过数学推导，在快慢指针相遇之后，再安排一个指针从头遍历到新指针和慢指针相遇的时候就是入环的第一个节点，非常巧妙
- `LinkedListQuestion07.removeNthFromEnd` 通过让快指针先走N步，从而快速的找到倒数的第N个元素的位置，而不用求解链表长度
- `LinkedListQuestion08.middleNode` 通过让快指针每次走两步，慢指针每次走一步的方式快速的找出链表的中间结点

### 特殊的双指针：滑动窗口

- `Question03.getLongestSubStringBySlidingWindow`
  在这个代码里面使用了一个Set作为窗口，用来记录最长的不重复子串的具体字符集。
  一般情况下滑动窗口的代码框架如下：

```java
// 滑动窗口算法伪码框架
void slidingWindow(String s) {
    // 用合适的数据结构记录窗口中的数据, 比如说, 我想记录窗口中元素出现的次数，就用 map 我想记录窗口中的元素和，就可以只用一个 int
    // 甚至你都可以用面向对象的思维把窗口理解为一个对象.
    // 这个窗口对象里面的一些属性就是你要关注的东西，这个窗口对象有一个进窗口的方法和一个出窗口的方法，进窗口和出窗口可能会导致窗口属性值的变动
    Object window = 

    int left = 0, right = 0;
    while (right < s.length()) {
        // 进窗口
        char c = s[right];
        window.add(c);
        right++;

        // 改窗口属性值 进行窗口内数据的一系列更新（这里要记录窗口内的记录变化，比如无重复字符子串问题，这里就需要记录子串的长度）

        // 判断左侧窗口是否要收缩（这里是问题的关键，什么时候进行窗口收缩，比如无重复子串问题中从left到right中出现重复字符就需要收缩窗口）
        while (left < right && window needs shrink){
            // d 是将移出窗口的字符
            char d = s[left];
            window.remove(d);
            // 缩小窗口 并进行窗口内数据的一系列更新 （收缩完窗口需要把窗口里面的各项数值修改下）
            left++;
            // 进行窗口属性值更新
        }
    }
}
```

相关的实战题目参考,全部套用了上面的模板：  
`Question18`  
`Question03.getLongestSubStringBySlidingWindowByTemplate`  
`com.ahu.coding.beast.training.Question16`  
`com.ahu.coding.beast.training.Question15`  

## 二分查找

二分查找是在数组有序情况下需要O(logN)的复杂度下最优选择。这道题目值得关注
`Question17`

## 单调栈
单调栈实际上就是栈，只是利用了一些巧妙的逻辑，使得每次新元素入栈后，栈内的元素都保持有序（单调递增或单调递减）。单调栈用途不太广泛，只处理一类典型的问题，比如「下一个更大元素」，「上一个更小元素」等
典型的例题就是：

`com.ahu.coding.beast.training.phase01.Question25`  
`com.ahu.coding.beast.training.phase01.Question27`  

此外还有一个变种的数据结构就是 单调队列，所谓单调队列 就是一个「队列」，只是使用了一点巧妙的方法，使得队列中的元素全都是单调递增（或递减）的。可以做了解
例题参考：


## 动态规划
动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，需要你熟练掌握递归思维，只有列出正确的「状态转移方程」，才能正确地穷举。
动态规划问题具有下面的三种重要特征，下面以斐波那契数列问题为例：  

**重叠子问题**：在求解斐波那契数列的题目中，为什么递归效率低呢就是因为有大量的重复计算一个，例如求解f(20) ，求解过程中f(17) 会被计算两次，这就是动态规划问题的第一个性质：重叠子问题，解决重叠问题可以使用「备忘录」来记录迭代过程的解，进行剪支，避免不必要的计算

**最优子结构**：显然斐波那契数列的子问题的解是能够逐步推到出最终的目标解的

**自底向上的递推关系**：这个性质也是最难的一点，斐波那契数列问题中递归的做法是你要求解f(n) 就先分解为 f(n-1) 和 f(n-2) ,逐步分解直到分解到f(1)和f(0)的递归出口，但是动态规划不是这样，动态规划则是从f(1)和f(2)开始逐步推导出f(n)的解

下面我们用动态规划的思路去解决斐波那契数列问题，代码如下：
```java
public static int fib(int n) {
  int[] dp = new int[n + 1];
  dp[0] = 0;
  dp[1] = 1;
  for (int i = 2; i < dp.length; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
  }
  return dp[n];
}
```
典型例题：

`com.ahu.coding.beast.training.phase_dp.Question20.maxSumAndArray`
`com.ahu.coding.beast.training.phase_dp.Question02.getCommonSubStringLength`
`com.ahu.coding.beast.training.phase_dp.Question07.longestCommonSequence`
`com.ahu.coding.beast.training.phase_dp.Question09`
`com.ahu.coding.beast.training.phase_dp.Question10`



## 贪心
贪心的时候有时候不能找到真正的解，比如说`Question09`中就错误的使用了贪心算法，导致部分case无法通过。

典型例题：
`Question23`