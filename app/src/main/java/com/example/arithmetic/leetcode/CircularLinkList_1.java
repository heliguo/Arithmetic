package com.example.arithmetic.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lgh on 2020/10/9:19:06
 * @description 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 */
public class CircularLinkList_1 {

    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new ListNode(-4);
        node.next.next.next.next = new ListNode(-4);
        node.next.next.next.next.next = node.next;
        System.out.println("1=======" + hasCycle1(node));
        System.out.println("2=======" + hasCycle2(node));
        System.out.println("3=======" + traverseMarkSolution(node));
    }

    /**
     * 方法一：使用hash表
     * set中不允许有重复值
     * 时间复杂度：O(N)，其中 NN 是链表中的节点数。最坏情况下我们需要遍历每个节点一次。
     * <p>
     * 空间复杂度：O(N)，其中 NN 是链表中的节点数。主要为哈希表的开销
     * ，最坏情况下我们需要将每个节点插入到哈希表中一次。
     *
     * @param node
     * @return
     */
    public static boolean hasCycle1(ListNode node) {
        if (node == null || node.next == null) {
            return false;
        }
        Set<ListNode> seen = new HashSet<>();
        while (node != null) {
            if (!seen.add(node)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 方法二：快慢指针，又称龟兔赛跑算法
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。
     * <p>
     * 当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。
     * <p>
     * 当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
     * <p>
     * 空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 方法三：递归标记法
     *
     * @param head
     * @return
     */
    private static boolean traverseMarkSolution(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.val == 0xcafebabe) {
            return true;
        }
        head.val = 0xcafebabe;
        return traverseMarkSolution(head.next);
    }


    static class ListNode {

        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }

    }

}
