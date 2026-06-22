package com.graph;

import java.util.*;

public class MinCostToConnectAllPoints {

    public static void main(String[] args) {

        MinCostToConnectAllPoints obj = new MinCostToConnectAllPoints();
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int result = obj.minCostConnectPoints(points);
        System.out.println("Minimum cost to connect all points: " + result);
    }

    public int minCostConnectPoints(int[][] points) {
        int size = points.length;
        ArrayList<ArrayList<int[]>> adj = createAdjList(points, size);

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((t1, t2) -> t1[1] - t2[1]);
        pq.add(new int[]{0, 0});
        boolean[] vis = new boolean[size];
        int[] parent = new int[size];
        int cost = 0;
        parent[0] = -1;
        int count = 0;

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int wt = poll[1];
            int node = poll[0];

            if (vis[node] == false) {
                count = count + 1;
                vis[node] = true;

                cost = cost + wt;
                if (count == size) {
                    return cost;
                }
                ArrayList<int[]> neigh = adj.get(node);
                for (int[] n : neigh) {
                    int to = n[0];
                    int w = n[1];
                    if (vis[to] == false) {
                        pq.add(new int[]{to, w});
                        parent[to] = node;
                    }
                }
            }
        }

        return cost;

    }

    ArrayList<ArrayList<int[]>> createAdjList(int[][] points, int size) {

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            adj.add(new ArrayList<int[]>());
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                int dis = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                adj.get(i).add(new int[]{j, dis});
                adj.get(j).add(new int[]{i, dis});
            }
        }

        return adj;
    }


}