package com.string;

import java.util.Arrays;

public class PermutationInString {

    public static void main(String[] args) {
        PermutationInString ps = new PermutationInString();
        String s1 = "ab";
        String s2 = "eidbaooo";
        boolean result = ps.checkInclusion(s1, s2);
        System.out.println(result); // Output: true
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] arr1 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int ind = c - 'a';
            arr1[ind] = arr1[ind] + 1;
        }

        int[] arr2 = new int[26];
        for (int j = 0; j < s1.length(); j++) {
            char c = s2.charAt(j);
            int ind = c - 'a';
            arr2[ind] = arr2[ind] + 1;
        }

        if (Arrays.compare(arr1, arr2) == 0) {
            return true;
        }

        int s = 0;
        for (int j = s1.length(); j < s2.length(); j++) {
            char c1 = s2.charAt(s);
            int ind2 = c1 - 'a';
            arr2[ind2] = arr2[ind2] - 1;
            char c = s2.charAt(j);
            int ind = c - 'a';
            arr2[ind] = arr2[ind] + 1;


            if (Arrays.compare(arr1, arr2) == 0) {
                return true;
            }
            s++;
        }

        return false;
    }
}
