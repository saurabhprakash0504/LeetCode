package com.sweepLine;

//YT - https://www.youtube.com/watch?v=Qh8ZjL1RpLI

import java.util.ArrayList;

public class IntervalListIntersections {

    public static void main(String[] args) {

        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        IntervalListIntersections obj = new IntervalListIntersections();
        int[][] result = obj.intervalIntersection(firstList, secondList);
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        int len1 = firstList.length;
        int len2 = secondList.length;
        ArrayList<int[]> list = new ArrayList<>();
        while (i < len1 && j < len2) {

            int firstStart = firstList[i][0];
            int firstEnd = firstList[i][1];
            int secStart = secondList[j][0];
            int secEnd = secondList[j][1];

            if (firstEnd >= secStart || secEnd >= firstStart) {
                int start = Integer.max(firstStart, secStart);
                int end = Integer.min(firstEnd, secEnd);
                if (start <= end)
                    list.add(new int[]{start, end});
            }

            if (firstEnd <= secEnd) {
                i++;
            } else {
                j++;
            }
        }

        int[][] res = new int[list.size()][2];
        for (int ii = 0; ii < list.size(); ii++) {
            res[ii] = list.get(ii);
        }

        return res;
    }
}
