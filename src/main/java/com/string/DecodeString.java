package com.string;

import java.util.*;

public class DecodeString {

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        String s = "3[a]2[bc]";
        String decoded = ds.decodeString(s);
        System.out.println(decoded); // Output: accaccacc
    }

    public String decodeString(String s) {
        Stack<Integer> freqStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        int i = 0;
        StringBuffer res = new StringBuffer();

        while (i < s.length()) {
            char c = s.charAt(i);

            int val = (int) c;
            if (c == ']') {
                StringBuffer sb = new StringBuffer();
                while (!strStack.isEmpty() && !strStack.peek().equals("[")) {
                    sb.insert(0, strStack.pop());
                }
                if (!strStack.isEmpty())
                    strStack.pop();
                int freq = freqStack.pop();
                StringBuffer sb2 = new StringBuffer();
                //StringBuffer repeated = new StringBuffer();
                for (int j = 0; j < freq; j++) {
                    sb2.append(sb);
                }

                //fix
                // ⭐ FIX: merge with previous level
                if (!strStack.isEmpty() && !strStack.peek().equals("[")) {
                    String prev = strStack.pop();
                    strStack.push(prev + sb2.toString());
                } else {
                    strStack.push(sb2.toString());
                }
                res = sb2;
                // strStack.push(sb2.toString());

            } else if ((val >= 97 && val <= 122) || c == '[') {
                strStack.push(String.valueOf(c));
            } else if (Character.isDigit(c)) {
                // FIX #1: correctly parse multi-digit numbers
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                freqStack.push(num);
                continue; // important: skip normal increment
            }
            i++;
        }

        StringBuilder finalRes = new StringBuilder();
        while (!strStack.isEmpty()) {
            finalRes.insert(0, strStack.pop());
        }
        return finalRes.toString();
    }

}
