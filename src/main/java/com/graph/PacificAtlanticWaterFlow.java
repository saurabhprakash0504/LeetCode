package com.graph;

import java.util.*;

public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        PacificAtlanticWaterFlow obj = new PacificAtlanticWaterFlow();
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        System.out.println(obj.pacificAtlantic(heights));
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Queue<int[]> poQueue = new LinkedList<>();
        Queue<int[]> aoQueue = new LinkedList<>();

        int r = heights.length;
        int c = heights[0].length;
        boolean[][] vis1 = new boolean[r][c];
        boolean[][] vis2 = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            poQueue.add(new int[]{i, 0});
            vis1[i][0] = true;
            aoQueue.add(new int[]{i, c - 1});
            vis2[i][c - 1] = true;
        }
        for (int j = 0; j < c; j++) {
            poQueue.add(new int[]{0, j});
            vis1[0][j] = true;
            aoQueue.add(new int[]{r - 1, j});
            vis2[r - 1][j] = true;
        }


        HashSet<String> poList = bfs(poQueue, heights, vis1, r, c);
        HashSet<String> aoList = bfs(aoQueue, heights, vis2, r, c);

        List<List<Integer>> res = intersection(poList, aoList);

        return res;
    }

    HashSet<String> bfs(Queue<int[]> q, int[][] heights, boolean[][] vis, int row, int col) {
        HashSet<String> res = new HashSet<>();
        while (!q.isEmpty()) {

            int[] poll = q.poll();
            int r = poll[0];
            int c = poll[1];
            res.add(r + "," + c);

            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + r;
                int nc = dc[i] + c;

                if (nr >= 0 && nr < row && nc >= 0 && nc < col && vis[nr][nc] == false && heights[nr][nc] >= heights[r][c]) {
                    vis[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }


        }

        return res;
    }

    List<List<Integer>> intersection(HashSet<String> poList, HashSet<String> aoList) {
        // System.out.println("poList size "+ poList.size());
        // System.out.println("aoList size "+ aoList.size());
        List<List<Integer>> res = new ArrayList<>();

        for (String i : poList) {
            if (aoList.contains(i)) {
                ArrayList<Integer> l = new ArrayList<>();
                String[] arr = i.split(",");
                l.add(Integer.parseInt(arr[0]));
                l.add(Integer.parseInt(arr[1]));
                res.add(l);
            }
        }

        return res;
    }

}
