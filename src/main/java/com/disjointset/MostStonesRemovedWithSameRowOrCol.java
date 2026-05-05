package com.disjointset;

import java.util.*;

public class MostStonesRemovedWithSameRowOrCol {

    public static void main(String[] args) {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        MostStonesRemovedWithSameRowOrCol ms = new MostStonesRemovedWithSameRowOrCol();
        System.out.println(ms.removeStones(stones));
    }

    public int removeStones(int[][] stones) {
        int r = 0;
        int c = 0;
        for (int i = 0; i < stones.length; i++) {
            int rr = stones[i][0];
            int cc = stones[i][1];
            r = Integer.max(rr, r);
            c = Integer.max(cc, c);
        }

        int total = r + c + 1;
        Disjoint ds = new Disjoint(total);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            int row = stones[i][0];
            int col = stones[i][1];

            int rr = row;
            int cc = col + r + 1;

            int ultimate_row = ds.findUltimateParent(rr);
            int ultimate_col = ds.findUltimateParent(cc);
            set.add(ultimate_row);
            set.add(ultimate_col);
            if (ultimate_row == ultimate_col) {
                continue;
            } else {
                ds.unionBySize(rr, cc);

            }
        }

        HashSet<Integer> tt = new HashSet<>();

        for (Integer t : set) {
            int te = ds.findUltimateParent(t);
            tt.add(te);
        }

        return stones.length - tt.size();

    }
}
