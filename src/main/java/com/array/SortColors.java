package com.array;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
    }

    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            int val = nums[mid];

            if (val == 0) {
                swap(nums, low, mid);
                mid++;
                low++;
            } else if (val == 1) {
                mid++;
            } else if (val == 2) {
                swap(nums, mid, high);
                high--;
            }

        }

        System.out.println(Arrays.toString(nums));
    }

    void swap(int[] nums, int l, int e) {
        int tem = nums[l];
        nums[l] = nums[e];
        nums[e] = tem;
    }
}
