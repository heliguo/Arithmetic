package com.example.arithmetic.stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lgh on 2020/7/15:16:20
 * @description 栈有关
 */
public class StackMain {

    /**
     * 有效括号 ( [ { } ] )
     */
    public static boolean isValid(String input) {
        if (input.length() % 2 != 0) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (map.containsKey(c)) {
                Character topElement = stack.isEmpty() ? '#' : stack.pop();
                if (topElement != map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 最小栈
     */

    public static class MinStack {

        Node head;

        public MinStack() {
        }

        public void push(int x) {
            if (head == null) {
                head = new Node(x, x);
            } else {
                head = new Node(x, Math.min(x, head.min), head);
            }
        }

        public void pop() {
            if (head != null) {
                head = head.next;
            }
        }

        public int top() {
            return head.value;
        }

        public int min() {
            return head.min;
        }

        public static class Node {

            int value;

            int min;

            Node next;

            public Node(int value, int min) {
                this(value, min, null);
            }

            public Node(int value, int min, Node next) {
                this.value = value;
                this.min = min;
                this.next = next;
            }
        }
    }

    /**
     * 用队列实现栈
     */
    public static class QueueStack {

        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();

        private int top;

        public void push(int x) {
            q1.add(x);
            top = x;
        }

        public int pop() {
            while (q1.size() > 1) {
                top = q1.remove();
                q2.add(top);
            }
            q1.remove();
            //clear 后 队列为空
            //            q1.clear();
            //            q1 = new LinkedList<>();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return top;
        }

        public boolean isEmpty() {
            return q1.isEmpty();
        }

        public int top() {
            return top;
        }

    }

    /**
     * 栈实现队列
     */

    public static class StackQueue {

        private int front;

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        public void push(int x) {
            if (s1.isEmpty()) {
                front = x;
            }
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s2.push(x);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public void pop() {
            s1.pop();
            if (!s1.isEmpty()) {
                front = s1.peek();
            }
        }

        public boolean isEmpty() {
            return s1.isEmpty();
        }

        public int peek() {
            return front;
        }

    }


    public static void main(String[] args) {
        //        String a = "{{{]{}()[]}}}}";
        //        System.out.println(isValid(a));
        //        MinStack minStack = new MinStack();
        //        minStack.push(0);
        //        minStack.push(-1);
        //        minStack.push(2);
        //        minStack.push(-5);
        //        System.out.println("top=====>>>" + minStack.top());
        //        System.out.println("min=====>>>" + minStack.min());
        //        minStack.pop();
        //        System.out.println("top=====>>>" + minStack.top());
        //        QueueStack queueStack = new QueueStack();
        //        queueStack.push(1);
        //        queueStack.push(2);
        //        queueStack.push(3);
        //        queueStack.push(5);
        //        queueStack.push(8);
        //        queueStack.pop();
        //        System.out.println("top=====>>>" + queueStack.top);
        //        queueStack.pop();
        //        System.out.println("top=====>>>" + queueStack.top);

    }

}
