package com.array;

import java.util.HashMap;

public class LongestSubArrayWithEqualNumberOf0sAnd1s {

    //YT - Nikhil Lohia
    public static void main(String[] args) {
        int[] arr = {0, 1};
        LongestSubArrayWithEqualNumberOf0sAnd1s l = new LongestSubArrayWithEqualNumberOf0sAnd1s();
        System.out.println(l.findMaxLength(arr));
    }

    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int maxLen = 0;
        int prevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prevSum = prevSum + nums[i];
            if (map.containsKey(prevSum)) {
                int ind = map.get(prevSum);
                int temp = i - ind;
                maxLen = Integer.max(maxLen, temp);
            } else {
                map.put(prevSum, i);
            }
        }

        return maxLen;

    }


}
