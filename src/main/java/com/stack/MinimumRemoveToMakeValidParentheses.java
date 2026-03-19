package com.stack;

import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        MinimumRemoveToMakeValidParentheses obj = new MinimumRemoveToMakeValidParentheses();
        String s = "lee(t(c)o)de)";
        System.out.println(obj.minRemoveToMakeValid(s));
    }

    public String minRemoveToMakeValid(String s) {

        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    int ind = stack.peek();
                    if (s.charAt(ind) == '(') {
                        stack.pop();
                    } else {
                        stack.push(i);
                    }
                } else {
                    stack.push(i);
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        int ind = s.length() - 1;


        while (ind >= 0) {

            if (!stack.isEmpty()) {
                int p = stack.peek();
                if (p == ind) {
                    stack.pop();
                    ind--;
                } else {
                    sb.insert(0, s.charAt(ind));
                    ind--;
                }
            } else {
                sb.insert(0, s.charAt(ind));
                ind--;
            }
        }

        return sb.toString();

    }
}
