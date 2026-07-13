package com.dynamicProgramming;

import java.util.Arrays;

public class MinSwapsToMakeArrayIncreasing {

    public static void main(String[] args) {
        MinSwapsToMakeArrayIncreasing obj = new MinSwapsToMakeArrayIncreasing();
        int[] nums1 = {0, 3, 5, 8, 9};
        int[] nums2 = {2, 1, 4, 6, 9};
        System.out.println(obj.minSwap(nums1, nums2));
    }

    public int minSwap(int[] nums1, int[] nums2) {
        int[] n1 = new int[nums1.length + 1];
        int[] n2 = new int[nums2.length + 1];
        n1[0] = -1;
        n2[0] = -1;
        for (int i = 1; i < nums1.length + 1; i++) {
            n1[i] = nums1[i - 1];
            n2[i] = nums2[i - 1];
        }
        // return find(n1, n2, 1, 0);


        int[][] dp = new int[nums1.length + 2][2];
        for (int i = 0; i < nums1.length + 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        // return findMem(n1, n2, 1, 0, dp);

        return findTab(n1, n2);

    }


    int find(int[] n1, int[] n2, int ind, int isSmall) {
        if (ind == n1.length) {
            return 0;
        }

        int prev1 = n1[ind - 1];
        int prev2 = n2[ind - 1];
        if (isSmall == 1) {
            // swap(prev1, prev2);
            int temp = prev1;
            prev1 = prev2;
            prev2 = temp;
        }

        //NO SWAP
        int ans = Integer.MAX_VALUE;
        if (n1[ind] > prev1 && n2[ind] > prev2) {
            ans = Math.min(ans, find(n1, n2, ind + 1, 0));
        }

        //SWAP
        if (n1[ind] > prev2 && n2[ind] > prev1) {
            ans = Math.min(ans, 1 + find(n1, n2, ind + 1, 1));
        }

        return ans;
    }


    int findMem(int[] n1, int[] n2, int ind, int isSmall, int[][] dp) {
        if (ind == n1.length) {
            return 0;
        }

        if (dp[ind][isSmall] != -1) {
            return dp[ind][isSmall];
        }

        int prev1 = n1[ind - 1];
        int prev2 = n2[ind - 1];
        if (isSmall == 1) {
            // swap(prev1, prev2);
            int temp = prev1;
            prev1 = prev2;
            prev2 = temp;
        }

        //NO SWAP
        int ans = Integer.MAX_VALUE;
        if (n1[ind] > prev1 && n2[ind] > prev2) {
            ans = Math.min(ans, findMem(n1, n2, ind + 1, 0, dp));
        }

        //SWAP
        if (n1[ind] > prev2 && n2[ind] > prev1) {
            ans = Math.min(ans, 1 + findMem(n1, n2, ind + 1, 1, dp));
        }

        return dp[ind][isSmall] = ans;
    }


    int findTab(int[] nums1, int[] nums2) {

        int[][] dp = new int[nums1.length + 1][2];


        for (int ind = nums1.length - 1; ind >= 1; ind--) {
            for (int isSwapped = 1; isSwapped >= 0; isSwapped--) {

                int ans = Integer.MAX_VALUE;
                int prev1 = nums1[ind - 1];
                int prev2 = nums2[ind - 1];

                if (isSwapped == 1) {
                    int temp1 = prev1;
                    prev1 = prev2;
                    prev2 = temp1;
                }

                if (prev1 < nums1[ind] && prev2 < nums2[ind]) {
                    ans = dp[ind + 1][0];
                }

                if (prev1 < nums2[ind] && prev2 < nums1[ind]) {
                    ans = Integer.min(ans, 1 + dp[ind + 1][1]);
                }

                dp[ind][isSwapped] = ans;
            }
        }

        return dp[1][0];
    }
}
