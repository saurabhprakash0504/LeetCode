package com.graph;

import java.util.*;

public class WordLadder2 {
    public static void main(String[] args) {

        WordLadder2 obj = new WordLadder2();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(obj.findLadders(beginWord, endWord, wordList));

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> set = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        Queue<List<String>> q = new LinkedList<>();
        q.add(temp);
        while (!q.isEmpty()) {
            int size = q.size();
            int i = 0;
            HashSet<String> usedThisLevel = new HashSet<>();
            while (i < size) {
                List<String> poll = q.poll();
                String last = poll.get(poll.size() - 1);
                for (int j = 0; j < last.length(); j++) {
                    for (int k = 0; k < 26; k++) {
                        String prev = last.substring(0, j);
                        char mid = (char) (k + 97);
                        String later = last.substring(j + 1);
                        String dummy = prev + mid + later;

                        if (set.contains(dummy)) {
                            List<String> dam = new ArrayList<>(poll);
                            dam.add(dummy);
                            q.add(dam);
                            if (dummy.equals(endWord)) {
                                // found = true;
                                res.add(dam);
                            }
                            usedThisLevel.add(dummy);
                        }
                    }
                }
                i++;
            }

            for (String w : usedThisLevel) {
                set.remove(w);
            }

        }
        return res;
    }
}
