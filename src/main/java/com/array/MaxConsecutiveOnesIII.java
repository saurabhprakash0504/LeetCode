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
        int l = 0;
        int r = 0;
        int noOFZero = 0;
        int maxLen = 0;
        while (r < nums.length) {
            int val = nums[r];
            if (val == 0) {
                noOFZero++;
            }

            if (noOFZero <= k) {
                int len = r - l + 1;
                maxLen = Integer.max(len, maxLen);
            } else {
                while (noOFZero > k) {
                    int val2 = nums[l];
                    if (val2 == 0) {
                        noOFZero--;
                    }
                    l++;
                    break;
                }
            }

            r++;
        }
        return maxLen;
    }
}
