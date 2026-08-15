package com.array;

import java.util.*;

public class LongestSubArrayWithSumK {

    //YT - Nikhil Lohia
    public static void main(String[] args) {
        LongestSubArrayWithSumK obj = new LongestSubArrayWithSumK();
        int[] arr = {1, 2, 3, 4, 5};
        int k = 9;
        System.out.println(obj.longestSubarray(arr, k));
    }

    public int longestSubarray(int[] arr, int k) {
        // code here
        HashMap<Integer, Integer> map = new HashMap<>();
        // map.put(0, -1);
        int preSum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            preSum = preSum + arr[i];
            int diff = preSum - k;
            if (preSum == k) {
                maxLen = Integer.max(maxLen, i + 1);
            } else {
                if (map.containsKey(diff)) {
                    int temp = map.get(diff);
                    maxLen = Integer.max(maxLen, i - temp);
                }
            }

            if (!map.containsKey(preSum))
                map.put(preSum, i);
        }

        return maxLen;
    }
}
