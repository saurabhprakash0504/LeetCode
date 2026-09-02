package com.karat;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;
import java.util.*;
//import javafx.util.Pair;

/*

You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
    ['b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'],
    ['b', 'a', 'c', 'c', 'e', 's', 'c', 'n'],
    ['a', 'l', 't', 'e', 'w', 'c', 'e', 'w'],
    ['a', 'l', 'o', 's', 's', 'e', 'c', 'c'],
    ['w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'],
    ['i', 'b', 'w', 'o', 'w', 'w', 'o', 'w']
]
word1_1 = "access"      # [(1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]
word1_2 = "balloon"     # [(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (1, 7)]

word1_3 = "wow"         # [(4, 3), (5, 3), (5, 4)] OR
                        # [(5, 2), (5, 3), (5, 4)] OR
                        # [(5, 5), (5, 6), (5, 7)]

word1_4 = "sec"         # [(3, 4), (3, 5), (3, 6)] OR
                        # [(3, 4), (3, 5), (4, 5)]

word1_5 = "bbaal"       # [(0, 0), (1, 0), (2, 0), (3, 0), (3, 1)]


grid2 = [
  ['a'],
]
word2_1 = "a"

grid3 = [
    ['c', 'a'],
    ['t', 't'],
    ['h', 'a'],
    ['a', 'c'],
    ['t', 'g']
]
word3_1 = "cat"
word3_2 = "hat"

grid4 = [
    ['c', 'c', 'x', 't', 'i', 'b'],
    ['c', 'a', 't', 'n', 'i', 'i'],
    ['a', 'x', 'n', 'x', 'p', 't'],
    ['t', 'x', 'i', 'x', 't', 't']
]
word4_1 = "catnip"      # [(1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)] OR
                        # [(0, 1), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)]


All test cases:

search(grid1, word1_1) => [(1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]
search(grid1, word1_2) => [(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (1, 7)]
search(grid1, word1_3) => [(4, 3), (5, 3), (5, 4)] OR
                          [(5, 2), (5, 3), (5, 4)] OR
                          [(5, 5), (5, 6), (5, 7)]
search(grid1, word1_4) => [(3, 4), (3, 5), (3, 6)] OR
                          [(3, 4), (3, 5), (4, 5)]
search(grid1, word1_5) => [(0, 0), (1, 0), (2, 0), (3, 0), (3, 1)]

search(grid2, word2_1) => [(0, 0)]

search(grid3, word3_1) => [(0, 0), (0, 1), (1, 1)]
search(grid3, word3_2) => [(2, 0), (3, 0), (4, 0)]

search(grid4, word4_1) => [(1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)] OR
                          [(0, 1), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)]

Complexity analysis variables:

r = number of rows
c = number of columns
w = length of the word
*/


public class TestInterview {
    public static void main(String[] argv) {
        char[][] grid1 = {
                {'b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'},
                {'b', 'a', 'c', 'c', 'e', 's', 'c', 'n'},
                {'a', 'l', 't', 'e', 'w', 'c', 'e', 'w'},
                {'a', 'l', 'o', 's', 's', 'e', 'c', 'c'},
                {'w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'},
                {'i', 'b', 'w', 'o', 'w', 'w', 'o', 'w'},
        };
        // String word1_1 = "access";   // [(1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]
        //  String word1_2 = "balloon";    // [(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (1, 7)]
        String word1_3 = "wow";  // [(4, 3), (5, 3), (5, 4)] OR
        // [(5, 2), (5, 3), (5, 4)] OR
        // [(5, 5), (5, 6), (5, 7)]
        ArrayList<int[]> res = find(grid1, word1_3);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i)[0] + " " + res.get(i)[1]);
        }
        // System.out.println(res);

        /*String word1_2 = "balloon";
        String word1_3 = "wow";
        String word1_4 = "sec";
        String word1_5 = "bbaal";

        char[][] grid2 = {
                {'a'},
        };
        String word2_1 = "a";

        char[][] grid3 = {
                {'c', 'a'},
                {'t', 't'},
                {'h', 'a'},
                {'a', 'c'},
                {'t', 'g'},
        };
        String word3_1 = "cat";
        String word3_2 = "hat";

        char[][] grid4 = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'a', 't', 'n', 'i', 'i'},
                {'a', 'x', 'n', 'x', 'p', 't'},
                {'t', 'x', 'i', 'x', 't', 't'},
        };
        String word4_1 = "catnip";*/
    }

    static ArrayList<int[]> find(char[][] grid, String word) {


        int r = grid.length;
        int c = grid[0].length;

        // boolean[][] vis = new boolean[r][c];


        char ch = word.charAt(0);
        for (int j = 0; j < r; j++) {
            for (int k = 0; k < c; k++) {
                if (grid[j][k] == ch) {
                    boolean[][] vis = new boolean[r][c];
                    int[] rr = {1, 0};
                    int[] cc = {0, 1};
                    ArrayList<int[]> list = new ArrayList<>();
                    vis[j][k] = true;
                    list.add(new int[]{j, k});
                    // System.out.println("temp  "+j + " " + k);
                    if (check(grid, j, k, vis, word, 1, list, rr, cc)) {
                        return list;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    static boolean check(char[][] grid, int r, int c, boolean[][] vis, String word, int index, ArrayList<int[]> list, int[] rr, int[] cc) {

        if (index == word.length()) {
            // System.out.println("here  "+index);
            return true;
        }


        for (int i = 0; i < 2; i++) {
            int nr = r + rr[i];
            int nc = c + cc[i];
            if (isSafe(nr, nc, word, vis, grid, index)) {
                vis[nr][nc] = true;
                list.add(new int[]{nr, nc});
                if (check(grid, nr, nc, vis, word, index + 1, list, rr, cc)) {
                    return true;
                }
                vis[nr][nc] = false;
                //list.add(new int[]{nr, nc});
            }


        }

        return false;


    }

    static boolean isSafe(int nr, int nc, String word, boolean[][] vis, char[][] grid, int index) {

        if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && vis[nr][nc] == false && grid[nr][nc] == word.charAt(index)) {
            return true;
        } else {
            return false;
        }
    }


    //Question 1 - find the subsets of a
    // words = ["cat", "dog", "taco", "apple"]
    // letters = "tacafd"
    //ANS = "cat"
    /*boolean check(char[][] grid, int r, int c, boolean[][] vis, String word){
        ArrayList<int[]> al = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        int[] rr ={1,0};
        int[] cc ={0,1};
        for(int i=0;i<grid.length;i++){
            if(grid[r][c] == word.charAt(i)){
                vis[r][c] = true;
                q.offer(new int[]{r,c});
                al.add(new int[]{r,c});
                while(!q.isEmpty()){
                    int[] curr = q.poll();
                    for(int t=0;t< 2;t++) {
                        int nr = curr[0] + rr[t];
                        int nc = curr[1] + cc[t];
                        boolean res = isSafe(nr, nc, grid, )
                    }


                }
            }
        }

    }

    boolean isSafe(int r, int c, )*/





   /* static String find(String[] words,String note){

        int[] noteArr = new int[26];
        for(int i=0;i< note.length();i++){
            char c = note.charAt(i);
            int ind = (int) (c) - 97;
            noteArr[ind] = noteArr[ind]+1;
        }

        for(int j=0;j<words.length;j++){
            String word = words[j];
            int[] wordArr = new int[26];
            for(int i=0;i< word.length();i++){
                char c = word.charAt(i);
                int ind = (int) (c) - 97;
                wordArr[ind] = wordArr[ind]+1;
            }
            // System.out.println("noteArr << "+ Arrays.toString(noteArr));
            //  System.out.println("wordArr << "+ Arrays.toString(wordArr) );
            boolean isValid = true;
            for(int k=0;k < 26;k++){
                if(wordArr[k] > noteArr[k]){
                    isValid = false;
                    break;
                }
            }
            // System.out.println("isValid << "+ isValid + " word "+ word +" note " + note );
            if(isValid)
                return word;
        }

        return "-";
    }*/
}

