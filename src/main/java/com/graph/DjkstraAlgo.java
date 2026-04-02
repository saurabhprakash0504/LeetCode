package com.graph;

import java.lang.reflect.Array;
import java.util.*;

public class DjkstraAlgo {


    public static void main(String[] args) {
        int V = 3;
        int src = 2;

        int[][] edges = {
                {0, 1, 1},
                {1, 2, 3},
                {0, 2, 6}
        };

        int[] dist = dijkstra(V, edges, src);
        System.out.println(Arrays.toString(dist));
    }

    public static int[] dijkstra(int V, int[][] edges, int src) {

        // STEP 1: Build adjacency list
        List<List<Sol>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph.get(u).add(new Sol(v, w));
            graph.get(v).add(new Sol(u, w)); // undirected
        }

        // STEP 2: Distance array
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // STEP 3: Min-heap (priority queue)
        PriorityQueue<Sol> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Sol(src, 0));

        // STEP 4: Dijkstra loop
        while (!pq.isEmpty()) {
            Sol curr = pq.poll();
            int node = curr.vertex;
            int currDist = curr.weight;

            // Skip outdated entries
            if (currDist > dist[node]) continue;

            // Explore neighbours
            for (Sol nei : graph.get(node)) {
                int newDist = currDist + nei.weight;

                if (newDist < dist[nei.vertex]) {
                    dist[nei.vertex] = newDist;
                    pq.add(new Sol(nei.vertex, newDist));
                }
            }
        }

        return dist;
    }
}

class Sol {
    int weight;
    int vertex;

    Sol(int n, int w) {
        weight = w;
        vertex = n;
    }
}