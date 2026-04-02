package com.graph;

import java.util.ArrayList;

public class DFS {

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        //   addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 1, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 1, 4);

        System.out.println("Following is Breadth First Traversal: ");
        ArrayList<Integer> l = dfs(adj);
        System.out.println(l);
    }

    static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[adj.size()];

        ArrayList<Integer> finalList = new ArrayList<>();

        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i])
                dfsTraversal(adj, i, visited, finalList);
        }

        return finalList;
    }

    static void dfsTraversal(ArrayList<ArrayList<Integer>> adj, int startIndex, boolean[] visited, ArrayList<Integer> finalList) {

        finalList.add(startIndex);
        visited[startIndex] = true;

        for (int i = 0; i < adj.get(startIndex).size(); i++) {
            if (!visited[adj.get(startIndex).get(i)]) {
                dfsTraversal(adj, adj.get(startIndex).get(i), visited, finalList);
            }
        }
    }

}
