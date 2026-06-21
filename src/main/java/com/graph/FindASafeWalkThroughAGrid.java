package com.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindASafeWalkThroughAGrid {

    public static void main(String[] args) {
        FindASafeWalkThroughAGrid obj = new FindASafeWalkThroughAGrid();
        List<List<Integer>> grid = Arrays.asList(
                Arrays.asList(0, 0, 1),
                Arrays.asList(0, 1, 0),
                Arrays.asList(0, 0, 0)
        );
        int health = 3;

        boolean result = obj.findSafeWalk(grid, health);
        System.out.println("Can reach the end safely: " + result);
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        Queue<Temp> q = new LinkedList<>();

        if (grid.get(0).get(0) == 1) {
            health--;
        }
        if (health < 1) return false;

        int er = grid.size() - 1;
        int ec = grid.get(0).size() - 1;

        int[][] best = new int[er + 1][ec + 1];
        for (int[] row : best) Arrays.fill(row, -1);

        best[0][0] = health;


        q.add(new Temp(0, 0, health));


        boolean[][] vis = new boolean[er + 1][ec + 1];
        vis[0][0] = true;

        int[] rr = {-1, 1, 0, 0};
        int[] cc = {0, 0, -1, 1};


        while (!q.isEmpty()) {

            Temp temp = q.poll();

            int r = temp.r;
            int c = temp.c;
            int h = temp.h;

            if (r == er && c == ec && h >= 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int tr = r + rr[i];
                int tc = c + cc[i];

                if (tr >= 0 && tr <= er && tc >= 0 && tc <= ec) {
                    if (grid.get(tr).get(tc) == 1) {
                        int newHealth = h - 1;
                        if (newHealth >= 1 && newHealth > best[tr][tc]) {
                            best[tr][tc] = newHealth;
                            q.add(new Temp(tr, tc, newHealth));
                        }

                        // vis[tr][tc] = true;
                        // if(h-1 >=1)
                        // q.add(new Temp(tr, tc, h-1 ));
                    } else {
                        int newHealth = h;
                        if (newHealth >= 1 && newHealth > best[tr][tc]) {
                            best[tr][tc] = newHealth;
                            q.add(new Temp(tr, tc, newHealth));
                        }
                    }

                }
            }


        }

        return false;
    }
}

class Temp {
    int r;
    int c;
    int h;

    Temp(int rr, int cc, int hh) {
        r = rr;
        c = cc;
        h = hh;
    }
}
