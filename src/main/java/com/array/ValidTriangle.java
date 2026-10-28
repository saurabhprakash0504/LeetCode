package com.array;

import java.util.Arrays;

public class ValidTriangle {

    public static void main(String[] args) {
        ValidTriangle vt = new ValidTriangle();
        int[] nums = {2, 2, 3, 4};
        System.out.println(vt.triangleNumber(nums));
    }

    public int triangleNumber(int[] nums) {

        int count = 0;
        Arrays.sort(nums);
        for (int k = nums.length - 1; k >= 2; k--) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += j - i;   // every index from i..j-1 pairs with j
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }
}
