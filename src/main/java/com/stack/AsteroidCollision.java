package com.stack;

import java.util.Stack;

public class AsteroidCollision {

    //YT - TakeUForward
    public static void main(String[] args) {
        AsteroidCollision asteroidCollision = new AsteroidCollision();
        int[] asteroids = {5, 10, -5};
        int[] res = asteroidCollision.asteroidCollision(asteroids);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i : asteroids) {

            if (i >= 0) {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && stack.peek() < 0) {
                    stack.push(i);
                } else {

                    while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(i)) {
                        stack.pop();
                    }
                    if (!stack.isEmpty() && stack.peek() == Math.abs(i)) {
                        stack.pop();
                    } else if (stack.isEmpty() || stack.peek() < 0) {
                        stack.push(i);
                    }

                }
            }

        }

        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return res;
    }
}
