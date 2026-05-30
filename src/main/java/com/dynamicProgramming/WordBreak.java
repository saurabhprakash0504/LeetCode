package com.dynamicProgramming;

import java.util.*;

public class WordBreak {

    public static void main(String[] args) {
        WordBreak obj = new WordBreak();
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(obj.wordBreak(s, wordDict));

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        // return find(s, 0, set);
        Boolean[] dp = new Boolean[s.length()];
        return findMem(s, 0, set, dp);
    }

    boolean find(String s, int startInd, HashSet<String> set) {

        if (startInd >= s.length()) {
            return true;
        }

        if (set.contains(s.substring(startInd))) {
            return true;
        }

        for (int i = startInd + 1; i < s.length(); i++) {

            String sub = s.substring(startInd, i);
            if (set.contains(sub) && find(s, i, set)) {
                return true;
            }
        }

        return false;
    }


    boolean findMem(String s, int startInd, HashSet<String> set, Boolean[] dp) {

        if (startInd >= s.length()) {
            return true;
        }

        if (dp[startInd] != null) {
            return dp[startInd];
        }

        for (int endInd = startInd + 1; endInd <= s.length(); endInd++) {
            String sub = s.substring(startInd, endInd);
            if (set.contains(sub) && findMem(s, endInd, set, dp)) {
                return dp[startInd] = true;
            }
        }

        return dp[startInd] = false;
    }
}
