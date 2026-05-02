package com.dynamicProgramming;

import java.util.Arrays;

public class HouseRobbery {

    public static void main(String[] args) {
        HouseRobbery hr = new HouseRobbery();
        int[] nums = {1, 2, 3, 1};
        System.out.println(hr.rob(nums));
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        //   return findMem(nums, 0, dp);

        // return findTab(nums);

        return findTabSO(nums);
    }

    int find(int[] nums, int ind) {
        if (ind >= nums.length) {
            return 0;
        }

        int inc = nums[ind] + find(nums, ind + 2);

        int exc = find(nums, ind + 1);

        return Integer.max(inc, exc);
    }


    int findMem(int[] nums, int ind, int[] dp) {
        if (ind >= nums.length) {
            return 0;
        }

        if (dp[ind] != -1) {
            return dp[ind];
        }

        int inc = nums[ind] + findMem(nums, ind + 2, dp);

        int exc = findMem(nums, ind + 1, dp);

        return dp[ind] = Integer.max(inc, exc);
    }

    int findTab(int[] nums) {

        int[] dp = new int[nums.length + 2];

        for (int ind = nums.length - 1; ind >= 0; ind--) {
            int inc = nums[ind] + dp[ind + 2];

            int exc = dp[ind + 1];

            dp[ind] = Integer.max(inc, exc);
        }


        return dp[0];
    }


    int findTabSO(int[] nums) {

        int[] dp = new int[nums.length + 2];

        int next_next = 0;
        int next = 0;

        for (int ind = nums.length - 1; ind >= 0; ind--) {
            int inc = nums[ind] + next_next;

            int exc = next;

            int curr = Integer.max(inc, exc);

            next_next = next;
            next = curr;

        }


        return next;
    }
}
