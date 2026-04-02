package com.companyInterview.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MetaPlayerQuestion {

    public static void main(String[] args) {
        int players = 5;

        int[][] val = {{0, 1}, {1, 2}, {0, 3}, {3, 4}};

        int[] res = find(val, players);

        System.out.println(Arrays.toString(res));
    }

    static int[] find(int[][] val, int players) {
        int[] res = new int[players];
        boolean[] visited = new boolean[players];

        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            adj.add(new ArrayList<>());
            paths.add(new ArrayList<>());
        }

        for (int i = 0; i < val.length; i++) {
            adj.get(val[i][0]).add(val[i][1]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        res[0] = 0;
        paths.get(0).add(0);

        while (!q.isEmpty()) {
            Integer parent = q.poll();
            ArrayList<Integer> children = adj.get(parent);
            for (int child : children) {
                if (!visited[child]) {
                    ArrayList<Integer> temp = paths.get(parent);
                    // temp.add(child);
                    ArrayList<Integer> childPath = new ArrayList<Integer>(temp);
                    childPath.add(child);
                    paths.set(child, childPath);
                    visited[child] = true;
                    res[child] = res[parent] + 1;
                    q.add(child);
                }
            }
        }

        System.out.println("Printing path");
        for (int i = 0; i < paths.size(); i++) {
            System.out.println(paths.get(i));
        }

        System.out.println("path end");

        return res;
    }

}
