package com.example.arithmetic.summation;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author lgh on 2020/5/29 10:39
 * @description 链表  7 5 8 + 2 5 1 = 1009
 */
public class LinkSum {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    public static class ListNode {
        private int      val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @NonNull
        @Override
        public String toString() {
            if (next != null) {
                return "{" + val + "," + next.toString() + "}";
            }
            return "{" + val + ",null}";
        }
    }

    public void getNodeVal(ListNode node) {
        Log.e("TAG", "getNodeVal: " + node.toString());
        if (node.next != null) {
            getNodeVal(node.next);
        }
    }

}

