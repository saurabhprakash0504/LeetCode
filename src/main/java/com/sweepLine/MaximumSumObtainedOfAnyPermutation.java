package com.sweepLine;

import java.util.Arrays;

//Hint YT - https://www.youtube.com/watch?v=YniLXdtGp5w&pp=ugUEEgJoaQ%3D%3D

public class MaximumSumObtainedOfAnyPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        MaximumSumObtainedOfAnyPermutation obj = new MaximumSumObtainedOfAnyPermutation();
        int result = obj.maxSumRangeQuery(nums, new int[][]{{0, 1}, {1, 3}, {0, 2}});
        System.out.println(result); // Output: 19
    }

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        final int MOD = 1_000_000_007;

        Arrays.sort(nums);

        reverse(nums);

        int len = nums.length;
        int[] arr = new int[len];

        for (int i = 0; i < requests.length; i++) {
            int first = requests[i][0];
            int sec = requests[i][1];

            arr[first] = arr[first] + 1;
            if (sec + 1 < arr.length)
                arr[sec + 1] = arr[sec + 1] - 1;
        }

        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];           // prefix sum -> coverage count per index
        }

        Arrays.sort(arr);
        reverse(arr);

        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = (res + (long) nums[i] * arr[i]) % MOD;
        }
        return (int) res;

    }

    public void reverse(int[] a) {
        // length of an array
        int n = a.length;

        // swap the first half with the second half
        for (int i = 0; i < n / 2; i++) {

            // Store the first half elements temporarily
            int t = a[i];

            // Assign the first half
            // to the last half
            a[i] = a[n - i - 1];

            // Assign the last half
            // to the first half
            a[n - i - 1] = t;
        }
    }
}
