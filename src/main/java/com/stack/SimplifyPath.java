package com.stack;

import java.util.Stack;

public class SimplifyPath {

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        String res = new SimplifyPath().simplifyPath(path);
        System.out.println(res);
    }

    public String simplifyPath(String path) {

        Stack<String> stack = new Stack();
        String[] arr = path.split("/");
        for (String s : arr) {
            if (s.equals(".") || s.equals("")) {

            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }

            } else {
                stack.push(s);
            }

        }
        StringBuffer sb = new StringBuffer();
        for (String f : stack) {
            sb.append("/").append(f);
        }

        return (sb.length() == 0) ? "/" : sb.toString();


    }
}
