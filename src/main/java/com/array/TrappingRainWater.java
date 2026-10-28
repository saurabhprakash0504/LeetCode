package com.array;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] arr = {3, 0, 1, 2, 5, 2, 1, 6, 1, 2};
        method1(arr);
    }


    static void method1(int[] arr) {
        int[] maxStart = new int[arr.length];
        int[] maxEnd = new int[arr.length];
        int maxiStart = arr[0];
        maxStart[0] = arr[0];
        int maxiEnd = arr[arr.length - 1];
        maxEnd[arr.length - 1] = arr[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxiStart) {
                maxStart[i] = arr[i];
                maxiStart = arr[i];
            } else {
                maxStart[i] = maxiStart;
            }

            if (arr[arr.length - (i + 1)] > maxiEnd) {
                maxEnd[arr.length - (i + 1)] = arr[arr.length - (i + 1)];
                maxiEnd = arr[arr.length - (i + 1)];
            } else {
                maxEnd[arr.length - (i + 1)] = maxiEnd;
            }

        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + Integer.min(maxStart[i], maxEnd[i]) - arr[i];
        }
        System.out.println("total water stored >> " + sum);
    }
}
