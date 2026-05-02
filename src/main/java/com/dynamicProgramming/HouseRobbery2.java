package com.dynamicProgramming;

import java.util.Arrays;

public class HouseRobbery2 {

    public static void main(String[] args) {
        HouseRobbery2 hr = new HouseRobbery2();
        int[] nums = {1, 2, 3, 1};
        System.out.println(hr.rob(nums));
    }

    public int rob(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp1 = new int[nums.length];

        int[] dp2 = new int[nums.length];

        Arrays.fill(dp1, -1);

        Arrays.fill(dp2, -1);



        int left = findMem(nums, 0, nums.length-2, dp1);

        int right = findMem(nums, 1, nums.length-1, dp2);

        return Integer.max(left, right);

    }

    int findMem(int[] nums, int ind, int end , int[] dp){
        if(ind > end){
            return 0;
        }

        if(dp[ind] != -1){
            return dp[ind];
        }

        int inc = nums[ind] + findMem(nums, ind+2,end, dp);

        int exc =  findMem(nums, ind+1, end, dp);

        return dp[ind] = Integer.max(inc, exc);
    }
}
