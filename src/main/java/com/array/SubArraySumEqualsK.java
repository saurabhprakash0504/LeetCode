package com.array;

import java.util.HashMap;

public class SubArraySumEqualsK {

    public static void main(String[] args) {

        int[] arr = {1, 1, 1};
        int k = 2;
        SubArraySumEqualsK s = new SubArraySumEqualsK();
        System.out.println(s.subarraySum(arr, k));


    }

    //YT - Nikhil Lohia
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int totalSum = 0;
        map.put(0, 1);
        for (int i : nums) {
            totalSum = totalSum + i;
            int diff = totalSum - k;
            if (map.containsKey(diff)) {
                count = count + map.get(diff);
            }

            if (map.containsKey(totalSum)) {
                int c = map.get(totalSum);
                map.put(totalSum, c + 1);
            } else {
                map.put(totalSum, 1);
            }
        }

        return count;

    }
}
