package com.backtracking;

import java.util.*;

public class AllPathsFromSourceToTarget {

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};

        AllPathsFromSourceToTarget obj = new AllPathsFromSourceToTarget();
        List<List<Integer>> res = obj.allPathsSourceTarget(graph);
        System.out.println(res);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        int nodes = graph.length;

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        List<List<Integer>> res = new ArrayList<>();

        find(0, nodes - 1, graph, res, set);


        return res;
    }

    void find(int start, int end, int[][] graph, List<List<Integer>> res, LinkedHashSet<Integer> set) {
        set.add(start);
        if (start == end) {
            ArrayList<Integer> al = new ArrayList<Integer>(set);
            res.add(al);
            return;
        }

        int[] neigh = graph[start];

        for (int i : neigh) {
            if (!set.contains(i)) {
                find(i, end, graph, res, set);
            }
            set.remove(i);
        }

    }
}
