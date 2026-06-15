package com.graph;

import java.util.*;

public class EvaluateDivision {

    public static void main(String[] args) {

        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        EvaluateDivision solution = new EvaluateDivision();
        double[] results = solution.calcEquation(equations, values, queries);

        System.out.println(Arrays.toString(results));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, ArrayList<Edges>> adj = createAdjList(equations, values);
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            double result = find(query, adj);
            res[i] = result;
        }

        return res;

    }


    double find(List<String> query, Map<String, ArrayList<Edges>> adj) {

        String from = query.get(0);
        String to = query.get(1);
        if (!adj.containsKey(from) || !adj.containsKey(to)) {
            return -1.0;
        }

        if (from.equals(to)) {
            return 1.0;
        }


        HashSet<String> vis = new HashSet<>();

        Queue<Curr> q = new LinkedList<>();

        q.add(new Curr(from, 1));
        vis.add(from);

        while (!q.isEmpty()) {
            Curr poll = q.poll();

            String currNode = poll.currInd;
            double currVal = poll.val;

            List<Edges> neigh = adj.get(currNode);

            for (Edges edge : neigh) {

                String neighNode = edge.to;
                double neighVal = edge.val;

                if (neighNode.equals(to)) {
                    return neighVal * currVal;
                } else if (!vis.contains(neighNode)) {
                    vis.add(neighNode);
                    q.add(new Curr(neighNode, neighVal * currVal));
                }


            }
        }

        return -1.0;

    }


    Map<String, ArrayList<Edges>> createAdjList(List<List<String>> equations, double[] values) {

        Map<String, ArrayList<Edges>> adj = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {

            List<String> list = equations.get(i);

            String from = list.get(0);
            String to = list.get(1);
            double val = values[i];

            if (adj.containsKey(from)) {
                ArrayList<Edges> l = adj.get(from);
                l.add(new Edges(to, val));
            } else {
                ArrayList<Edges> l = new ArrayList<>();
                l.add(new Edges(to, val));
                adj.put(from, l);
            }


            if (adj.containsKey(to)) {
                ArrayList<Edges> l = adj.get(to);
                l.add(new Edges(from, 1 / (val * 1.0)));
            } else {
                ArrayList<Edges> l = new ArrayList<>();
                l.add(new Edges(from, 1 / (val * 1.0)));
                adj.put(to, l);
            }

        }
        return adj;
    }
}


class Edges {

    String to;
    double val;

    Edges(String t, double v) {
        to = t;
        val = v;
    }
}

class Curr {

    String currInd;
    double val;

    Curr(String c, double v) {
        currInd = c;
        val = v;
    }
}