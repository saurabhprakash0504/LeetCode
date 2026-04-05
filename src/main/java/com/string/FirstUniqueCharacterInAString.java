package com.string;

import java.util.Arrays;

public class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        FirstUniqueCharacterInAString firstUniqueCharacterInAString = new FirstUniqueCharacterInAString();
        String s = "leetcodel";
        int result = firstUniqueCharacterInAString.firstUniqChar(s);
        System.out.println(result);
    }

    public int firstUniqChar(String s) {

        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'a';
            if (arr[ind] == -1) {
                arr[ind] = i;
            } else if (arr[ind] != -1) {
                arr[ind] = -2;
            }
        }

        int small = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (arr[i] != -1 && arr[i] != -2) {
                small = Integer.min(arr[i], small);
            }
        }

        if (small == Integer.MAX_VALUE) {
            return -1;
        } else {
            return small;
        }

    }
}
