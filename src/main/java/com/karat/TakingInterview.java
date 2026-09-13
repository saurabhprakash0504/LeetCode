package com.karat;

/**
 * THIS QUESTION WAS THE PART OF SECOND ROUND OF THE INTERVIEW.
 * INTERVIEWEE WAS A SEASONED INTERVIEWER AND THEY WERE MAKING MISTAKE BY CHOICE AND THEY WERE JUDGING ME HOW AM I HELPING THEM.
 *
 * HE WROTE THE CODE IN PYTHON
 *
 * HE MADE MISTAKES LIKE
 *
 * DOES NOT USE DEF IN THE PYTHON METHOD
 * DECREMENTING THE VALUE WHILE RETURNING
 * DIDNT CHECK FOR THE OUT OF BOUND CONDITION IN THE SECOND EXAMPLE
 * DIDNT SAVE THE VALUE IN THE VARIABLE
 * MAKING THE MISTAKE IN THE FOR LOOP AND WHILE LOOP
 */

public class TakingInterview {

    public static void main(String[] args) {
        int[][] image1 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        find(image1);


    }

    static void find(int[][] image) {
        int row = image.length;
        int col = image[0].length;

        int startR = -1;
        int startC = -1;
        int len = 0;
        int wid = 0;

        boolean found = false;
        outer:
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (image[i][j] == 0) {
                    startR = i;
                    startC = j;
                    //found = true;
                    break outer;
                }
            }

        }

        for (int j = startC; j < col; j++) {
            if (image[startR][j] == 0) {
                wid = wid + 1;
            }
        }

        for (int i = startR; i < row; i++) {
            if (image[i][startC] == 0) {
                len = len + 1;
            }
        }

        System.out.println("start row " + startR + " start col " + startC);
        System.out.println(" height " + len);
        System.out.println(" width " + wid);


    }

}
