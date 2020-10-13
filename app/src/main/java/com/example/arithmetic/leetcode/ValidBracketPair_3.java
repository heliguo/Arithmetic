package com.example.arithmetic.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author lgh on 2020/10/13:19:59
 * @description 有效括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 注意空字符串可被认为是有效字符串。
 */
public class ValidBracketPair_3 {

    static Map<Character, Character> pairs = new HashMap<Character, Character>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
    }};

    public static void main(String[] args) {
        String a = "( ) []{}";
        String b = "([{}])";
        String c = "([{}{])}";
        System.out.println(isValid1(a));
        System.out.println(isValid1(b));
        System.out.println(isValid1(c));
    }

    /**
     * 法一：栈
     * <p>
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
     * <p>
     * 空间复杂度：O(n + |Σ|)，其中 Σ 表示字符集，本题中字符串只包含 6 种括号，|Σ| = 6。
     * 栈中的字符数量为 O(n)，而哈希映射使用的空间为 O(∣Σ∣)，相加即可得到总空间复杂度
     */

    public static boolean isValid1(String s) {
        s = s.replaceAll(" ", "");//剔除空字符
        int len = s.length();
        if (len % 2 == 1 || len == 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            System.out.println("====  " + i);
            char c = s.charAt(i);
            if (pairs.containsKey(c)) {//如果是右括号
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {//出现右括号栈顶必须为相应左括号
                    return false;
                }
                stack.pop();
            } else {//如果是左括号，先入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
