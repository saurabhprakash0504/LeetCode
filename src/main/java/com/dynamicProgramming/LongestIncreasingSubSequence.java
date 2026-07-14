package com.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingSubSequence {

    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 7, 9, 1};

        System.out.println(findDPBinarySearch(arr));
    }

    static int lis(int arr[]) {
        // code here
        int len = arr.length;
        int ind = 0;
        int maxx = -1;
        int size = 0;
        //  return find(arr, len, ind, maxx);

        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], -1);
        }

        //   return findMem(arr, len, ind, maxx, dp);

        //   return findMem2(arr, len, ind, maxx, dp);

        //return findTab(arr, len);
        //  return findTabSC(arr, len);

        return findDPBinarySearch(arr);
    }

    static int find(int arr[], int len, int ind, int maxInd) {
        if (ind >= len) {
            return 0;
        }

        int inc = 0;
        if (maxInd == -1 || arr[ind] > arr[maxInd]) {
            inc = 1 + find(arr, len, ind + 1, ind);
        }
        int exc = find(arr, len, ind + 1, maxInd);

        return Integer.max(inc, exc);
    }


    static int findMem(int arr[], int len, int ind, int maxInd, int[][] dp) {
        if (ind >= len) {
            return 0;
        }

        if (maxInd != -1 && dp[ind][maxInd] != -1) {
            return dp[ind][maxInd];
        }

        int inc = 0;
        if (maxInd == -1 || arr[ind] > arr[maxInd]) {
            inc = 1 + findMem(arr, len, ind + 1, ind, dp);
        }
        int exc = findMem(arr, len, ind + 1, maxInd, dp);

        if (maxInd != -1) {
            return dp[ind][maxInd] = Integer.max(inc, exc);
        }
        return Integer.max(inc, exc);
    }


    static int findMem2(int arr[], int len, int ind, int maxInd, int[][] dp) {
        if (ind >= len) {
            return 0;
        }

        if (dp[ind][maxInd + 1] != -1) {
            return dp[ind][maxInd + 1];
        }

        int inc = 0;
        if (maxInd == -1 || arr[ind] > arr[maxInd]) {
            inc = 1 + findMem2(arr, len, ind + 1, ind, dp);
        }

        int exc = findMem2(arr, len, ind + 1, maxInd, dp);


        return dp[ind][maxInd + 1] = Integer.max(inc, exc);

    }


    static int findMem3(ArrayList<Integer> arr, int len, int ind, int maxInd, int[][] dp) {
        if (ind >= len) {
            return 0;
        }

        if (dp[ind][maxInd + 1] != -1) {
            return dp[ind][maxInd + 1];
        }

        int inc = 0;
        if (maxInd == -1 || arr.get(ind) > arr.get(maxInd)) {
            inc = 1 + findMem3(arr, len, ind + 1, ind, dp);
        }

        int exc = findMem3(arr, len, ind + 1, maxInd, dp);


        return dp[ind][maxInd + 1] = Integer.max(inc, exc);

    }


    static int findTab(int[] arr, int len) {

        int[][] dp = new int[len + 1][len + 1];

        for (int ind = len - 1; ind >= 0; ind--) {
            for (int maxInd = len - 1; maxInd >= -1; maxInd--) {

                int inc = 0;
                if (maxInd == -1 || arr[ind] > arr[maxInd]) {
                    inc = 1 + dp[ind + 1][ind + 1];
                }

                int exc = dp[ind + 1][maxInd + 1];


                dp[ind][maxInd + 1] = Integer.max(inc, exc);
            }
        }

        return dp[0][0];
    }


    static int findTabSC(int[] arr, int len) {

        // int[][] dp = new int[len+1][len+1];
        int[] prev = new int[len + 1];
        int[] curr = new int[len + 1];

        for (int ind = len - 1; ind >= 0; ind--) {
            for (int maxInd = len - 1; maxInd >= -1; maxInd--) {

                int inc = 0;
                if (maxInd == -1 || arr[ind] > arr[maxInd]) {
                    inc = 1 + prev[ind + 1];
                }

                int exc = prev[maxInd + 1];


                curr[maxInd + 1] = Integer.max(inc, exc);
            }

            prev = curr;

        }

        return prev[0];
    }

    //TODO:: Using Binary seach tree
    static public int findDPBinarySearch(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {

            if (al.get(al.size() - 1) < nums[i]) {
                al.add(nums[i]);
            } else {
                int lowestInd = binarySearch(al, nums[i]);
                if (lowestInd != -1) {
                    al.set(lowestInd, nums[i]);
                }
            }

        }

        return al.size();
    }

    static int binarySearch(ArrayList<Integer> al, int num) {
        int start = 0;
        int end = al.size() - 1;
        int ind = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (al.get(mid) >= num) {
                ind = mid;
                end = mid - 1;
            } else {

                start = mid + 1;
            }
        }

        return ind;
    }
}
