package com.array;

public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        MaxConsecutiveOnesIII maxConsecutiveOnesIII = new MaxConsecutiveOnesIII();
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        int res = maxConsecutiveOnesIII.longestOnes(nums, k);
        System.out.println("res >> " + res);
    }

    public int longestOnes(int[] nums, int k) {
        int l =0;
        int r = 0;
        int count_0 = 0;
        int currLen = 0;
        int maxLen = Integer.MIN_VALUE;
        while(r < nums.length){

            if(nums[r] == 1){
                currLen = r - l +1;
            }else {
                if(count_0 == k){
                    currLen = r - l;
                    while(l<r && nums[l] != 0){
                        l++;
                    }
                    l= l+1;
                }else {
                    count_0++;
                    currLen = r - l+1;
                }
            }
            maxLen = Integer.max(maxLen, currLen);
            r = r +1;

        }
        return maxLen;
    }
}
