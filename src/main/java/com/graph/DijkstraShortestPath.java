package com.graph;

import java.util.*;

public class DijkstraShortestPath {

    public static void main(String[] args) {
        DijkstraShortestPath obj = new DijkstraShortestPath();
        int V = 5;
        int[][] edges = {
                {0, 1, 2},
                {0, 2, 4},
                {1, 2, 1},
                {1, 3, 7},
                {2, 4, 3},
                {3, 4, 1}
        };
        int src = 0;
        int[] result = obj.dijkstra(V, edges, src);
        System.out.println("Shortest distances from source " + src + ": " + Arrays.toString(result));
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        ArrayList<ArrayList<TempD>> adj = createAdjList(V, edges);
        PriorityQueue<TempD> pq = new PriorityQueue<TempD>((pq1, pq2) -> pq1.w - pq2.w);
        pq.add(new TempD(src, 0));

        boolean[] vis = new boolean[V];
        int[] dis = new int[V];
        Arrays.fill(dis, -1);
        dis[src] = 0;

        //  int cost = 0;

        while (!pq.isEmpty()) {

            TempD poll = pq.poll();
            int edge = poll.t;
            int wt = poll.w;


            if (vis[edge] == false) {
                vis[edge] = true;
                dis[edge] = wt;
                ArrayList<TempD> neigh = adj.get(edge);

                for (TempD t : neigh) {
                    if (vis[t.t] == false) {
                        pq.add(new TempD(t.t, wt + t.w));
                    }
                }
            }

        }

        return dis;
    }

    ArrayList<ArrayList<TempD>> createAdjList(int v, int[][] edges) {

        ArrayList<ArrayList<TempD>> adj = new ArrayList<ArrayList<TempD>>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<TempD>());
        }

        for (int i = 0; i < edges.length; i++) {
            int to = edges[i][0];
            int from = edges[i][1];
            int wt = edges[i][2];

            adj.get(to).add(new TempD(from, wt));
            adj.get(from).add(new TempD(to, wt));
        }

        return adj;
    }

}

class TempD {
    // int f;
    int t;
    int w;

    TempD(int tt, int ww) {
        // f = ff;
        t = tt;
        w = ww;
    }
}

//}

