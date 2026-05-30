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
        Boolean[] dp = new Boolean[s.length()];
        return findMem(s, 0, set, dp);
    }

    boolean find(String s, int ind, HashSet<String> set) {

        if (ind >= s.length()) {
            return true;
        }

        if (set.contains(s.substring(ind))) {
            return true;
        }

        for (int i = ind + 1; i < s.length(); i++) {
            // if(ind+i < s.length()){
            String sub = s.substring(ind, i);
            if (set.contains(sub) && find(s, i, set)) {
                return true;
            }
            //  }
        }

        return false;
    }


    boolean findMem(String s, int ind, HashSet<String> set, Boolean[] dp) {

        if (ind >= s.length()) {
            return true;
        }

        if (set.contains(s.substring(ind))) {
            return true;
        }

        if (dp[ind] != null) {
            return dp[ind];
        }

        for (int i = ind + 1; i < s.length(); i++) {
            // if(ind+i < s.length()){
            String sub = s.substring(ind, i);
            if (set.contains(sub) && findMem(s, i, set, dp)) {
                return dp[ind] = true;
            }
            //  }
        }

        return dp[ind] = false;
    }
}
