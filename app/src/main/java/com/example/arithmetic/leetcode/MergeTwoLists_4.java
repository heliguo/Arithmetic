package com.example.arithmetic.leetcode;

/**
 * @author lgh on 2020/10/20:17:21
 * @description 合并两个有序列表
 */
public class MergeTwoLists_4 {

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(5);
        listNode1.next.next.next = new ListNode(9);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(2);
        listNode2.next.next = new ListNode(5);
        listNode2.next.next.next = new ListNode(7);

        print(mergeTwoLists2(listNode1, listNode2));
//        print(mergeTwoLists(listNode1, listNode2));


    }

    /**
     * 递归
     * <p>
     * 时间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），
     * 函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
     * <p>
     * 空间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，
     * 栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 迭代
     * <p>
     * 时间复杂度：O(n + m) ，其中 n 和 m 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中，
     * 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)。
     * <p>
     * 空间复杂度：O(1) 。我们只需要常数的空间存放若干变量。
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(-1);
        ListNode pre = listNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;

        return listNode.next;
    }

    static class ListNode {

        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }

    }

    static void print(ListNode l) {
        if (l == null)
            return;
        System.out.println(l.val);
        if (l.next != null) {
            print(l.next);
        }
    }

}
