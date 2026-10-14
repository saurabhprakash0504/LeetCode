package com.sweepLine;

import java.util.*;

public class DescribeThePainting {

    public static void main(String[] args) {

        int[][] segments = {{1, 4, 5}, {4, 7, 7}, {1, 7, 9}};
        DescribeThePainting obj = new DescribeThePainting();
        List<List<Long>> result = obj.splitPainting(segments);
        System.out.println(result); // Output: [[1, 2, 5], [2, 4, 8], [4, 6, 3], [8, 10, 2]]
    }

    public List<List<Long>> splitPainting(int[][] segments) {
        ArrayList<PaintEvent> al = new ArrayList<>();
        for (int i = 0; i < segments.length; i++) {
            al.add(new PaintEvent(segments[i][0], segments[i][2]));   // color starts
            al.add(new PaintEvent(segments[i][1], -segments[i][2]));  // color ends
        }

        Collections.sort(al, (a1, a2) -> Integer.compare(a1.pos, a2.pos));

        List<List<Long>> res = new ArrayList<>();
        long curr = 0;   // running sum of active colors
        int prev = 0;    // position of the previous event
        for (int i = 0; i < al.size(); i++) {
            PaintEvent e = al.get(i);

            // position changed -> close the previous piece (only if something was painted)
            if (i > 0 && e.pos != prev && curr > 0) {
                res.add(Arrays.asList((long) prev, (long) e.pos, curr));
            }

            curr = curr + e.delta;
            prev = e.pos;
        }

        return res;
    }
}

class PaintEvent {

    int pos;
    long delta;

    PaintEvent(int p, long d) {
        pos = p;
        delta = d;
    }
}
