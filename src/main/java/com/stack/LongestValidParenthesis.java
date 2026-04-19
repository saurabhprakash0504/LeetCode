package com.stack;

import java.util.Stack;

public class LongestValidParenthesis {

    public static void main(String[] args) {
        LongestValidParenthesis longestValidParenthesis = new LongestValidParenthesis();
        System.out.println(longestValidParenthesis.longestValidParentheses(")()())"));
    }

    public int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

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

        // If stack is empty → whole string is valid
        if (stack.isEmpty()) return s.length();

        int maxLen = 0;
        int end = s.length();

        // Compute max gap between invalid indices
        while (!stack.isEmpty()) {
            int start = stack.pop();
            maxLen = Math.max(maxLen, end - start - 1);
            end = start;
        }

        // Check segment before first invalid index
        maxLen = Math.max(maxLen, end);

        return maxLen;

    }
}
