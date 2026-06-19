package com.graph;

import java.util.*;

public class PathWithMaximumProbability {

    public static void main(String[] args) {

        PathWithMaximumProbability obj = new PathWithMaximumProbability();
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.2};
        int start_node = 0;
        int end_node = 2;

        double res = obj.maxProbability(n, edges, succProb, start_node, end_node);
        System.out.println(res);
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        ArrayList<ArrayList<Pair3>> adj = createAdj(n, edges, succProb);

        //  Double val = 1.0;
        boolean[] vis = new boolean[n];


        //   dfs(adj, vis, start_node, end_node, val);

        PriorityQueue<Pair3> pq = new PriorityQueue<Pair3>((pq1, pq2) -> Double.compare(pq2.wt, pq1.wt));
        pq.add(new Pair3(start_node, 1));
        //   vis[start_node] = true;

        double maxi = 1;
        double res = 0.0;
        while (!pq.isEmpty()) {

            Pair3 poll = pq.poll();
            int from = poll.edge;
            double fromWt = poll.wt;

            if (from == end_node) {
                return fromWt;
            }

            if (vis[from]) {
                continue;
            }
            vis[from] = true;

            ArrayList<Pair3> neigh = adj.get(from);

            for (Pair3 pair : neigh) {
                int to = pair.edge;
                double w = pair.wt;

                double temp = fromWt * w;

                if (!vis[to]) {
                    pq.add(new Pair3(to, temp));
                }

            }

        }


        return res;
    }


    ArrayList<ArrayList<Pair3>> createAdj(int n, int[][] edges, double[] succProb) {
        ArrayList<ArrayList<Pair3>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Pair3>());
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj.get(from).add(new Pair3(to, succProb[i]));
            adj.get(to).add(new Pair3(from, succProb[i]));
        }

        return adj;
    }
}

class Pair3 {
    int edge;
    double wt;

    Pair3(int e, double w) {
        edge = e;
        wt = w;
    }
}