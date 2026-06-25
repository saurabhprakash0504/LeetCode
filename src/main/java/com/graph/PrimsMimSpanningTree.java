package com.graph;

import java.util.*;

public class PrimsMimSpanningTree {

    public static void main(String[] args) {
        PrimsMimSpanningTree obj = new PrimsMimSpanningTree();
        int V = 5;
        int[][] edges = {
                {0, 1, 2},
                {0, 2, 4},
                {1, 2, 1},
                {1, 3, 7},
                {2, 4, 3},
                {3, 4, 1}
        };

        int result = obj.spanningTree(V, edges);
        System.out.println("Minimum Spanning Tree cost: " + result);
    }

    public int spanningTree(int V, int[][] edges) {
        // code here
        ArrayList<ArrayList<TempP>> adj = createAdjList(V, edges);
        PriorityQueue<TempP> pq = new PriorityQueue<TempP>((pq1, pq2) -> pq1.w - pq2.w);
        pq.add(new TempP(0, 0));

        boolean[] vis = new boolean[V];
        int[] parent = new int[V];
        parent[0] = -1;
        int cost = 0;

        while (!pq.isEmpty()) {

            TempP poll = pq.poll();
            int edge = poll.t;
            int wt = poll.w;

            if (vis[edge] == false) {
                cost = cost + wt;
                vis[edge] = true;
                ArrayList<TempP> neigh = adj.get(edge);

                for (TempP t : neigh) {
                    if (vis[t.t] == false) {
                        pq.add(new TempP(t.t, t.w));

                    }


                }
            }

        }

        return cost;
    }

    ArrayList<ArrayList<TempP>> createAdjList(int v, int[][] edges) {

        ArrayList<ArrayList<TempP>> adj = new ArrayList<ArrayList<TempP>>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<TempP>());
        }

        for (int i = 0; i < edges.length; i++) {
            int to = edges[i][0];
            int from = edges[i][1];
            int wt = edges[i][2];

            adj.get(to).add(new TempP(from, wt));
            adj.get(from).add(new TempP(to, wt));
        }

        return adj;
    }

}

class TempP {
    // int f;
    int t;
    int w;

    TempP(int tt, int ww) {
        // f = ff;
        t = tt;
        w = ww;
    }
}
