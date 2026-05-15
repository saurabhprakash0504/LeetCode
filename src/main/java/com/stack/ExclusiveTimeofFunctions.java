package com.stack;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeofFunctions {

    public static void main(String[] args) {
        ExclusiveTimeofFunctions exclusiveTimeofFunctions = new ExclusiveTimeofFunctions();
        List<String> logs = List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        int[] res = exclusiveTimeofFunctions.exclusiveTime(2, logs);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        int prevTime = 0;
        Stack<Integer> stack = new Stack<>();

        for (String s : logs) {
            String[] arr = s.split(":");
            int function = Integer.parseInt(arr[0]);
            String action = arr[1];
            int ind = Integer.parseInt(arr[2]);

            if (action.equals("start")) {
                if (!stack.isEmpty()) {
                    int top = stack.peek();
                    res[top] = res[top] + (ind - prevTime);
                }
                stack.push(function);
                prevTime = ind;
            } else {
                int top = stack.pop();
                res[top] = res[top] + (ind - prevTime + 1);
                prevTime = ind + 1;
            }
        }

        return res;
    }
}
