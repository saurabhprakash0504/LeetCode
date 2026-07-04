package com.graph;

import java.util.*;

public class MinHeightTree {

    public static void main(String[] args) {

    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        int[] inDegree = new int[n];
        ArrayList<ArrayList<Integer>> adj = createAdj(n, edges, inDegree);

        List<Integer> res = new ArrayList<>();

        if (edges.length == 0) {
            res.add(n - 1);
            return res;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1) {
                q.add(i);
                inDegree[i] = 0;
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            res.clear();
            while (size != 0) {

                int poll = q.poll();
                size--;
                res.add(poll);
                inDegree[poll] = 0;
                ArrayList<Integer> neigh = adj.get(poll);

                for (int nn : neigh) {
                    inDegree[nn] = inDegree[nn] - 1;
                    if (inDegree[nn] == 1) {
                        q.add(nn);
                    }
                }

            }
        }


        return res;

    }


    ArrayList<ArrayList<Integer>> createAdj(int n, int[][] edges, int[] inDegree) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            adj.get(u).add(v);
            adj.get(v).add(u);

            inDegree[u] = inDegree[u] + 1;
            inDegree[v] = inDegree[v] + 1;
        }

        return adj;
    }

}

