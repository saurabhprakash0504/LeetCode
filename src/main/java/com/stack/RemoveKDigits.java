package com.stack;

import java.util.Stack;

public class RemoveKDigits {

    public static void main(String[] args) {
        RemoveKDigits removeKDigits = new RemoveKDigits();
        String num = "1432219";
        int k = 3;
        String result = removeKdigits(num, k);
        System.out.println(result); // Output: "1219"
    }

    public static String removeKdigits(String num, int k) {

        Stack<Integer> stack = new Stack<>();

        for (char ch : num.toCharArray()) {

            int digit = ch - '0';

            while (!stack.isEmpty()
                    && k > 0
                    && stack.peek() > digit) {
                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        StringBuilder result = new StringBuilder();

        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        // Remove remaining digits from the end
        while (k > 0 && result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
            k--;
        }

        // Remove leading zeros
        while (result.length() > 0 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.length() == 0 ? "0" : result.toString();
    }
}
