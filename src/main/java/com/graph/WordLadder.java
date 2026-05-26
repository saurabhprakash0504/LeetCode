package com.graph;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        WordLadder obj = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(obj.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<String>(wordList);
        Queue<String> q = new LinkedList<String>();
        q.add(beginWord);
        set.remove(beginWord);
        int count = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            int i = 1;
            count++;
            while (i <= size) {

                String poll = q.poll();

                if (poll.equals(endWord)) {
                    return count;
                } else {
                    int len = poll.length();
                    for (int j = 0; j < len; j++) {
                        for (int k = 0; k < 26; k++) {
                            String prev = poll.substring(0, j);
                            char c = (char) (97 + k);
                            String later = poll.substring(j + 1);
                            String tem = prev + c + later;

                            if (set.contains(tem)) {
                                System.out.println("word >> " + tem);
                                set.remove(tem);
                                q.add(tem);
                            }
                        }
                    }
                }
                i++;
            }
        }

        return 0;
    }
}
