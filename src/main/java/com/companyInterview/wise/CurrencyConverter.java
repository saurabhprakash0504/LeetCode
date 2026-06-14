package com.companyInterview.wise;

import java.util.*;

public class CurrencyConverter {
//Graph BFS algo
//Claude code


    public static void main(String[] args) {
        String[][] rates = {{"USD", "JPY", "100"}, {"JPY", "CHN", "20"}, {"CHN", "THAI", "200"}};
        String[][] queries = {{"USD", "CHN"}, {"JPY", "THAI"}, {"USD", "AUD"}};

        CurrencyConverter solution = new CurrencyConverter();
        double[] output = solution.findConversionRates(rates, queries);

        System.out.println(Arrays.toString(output));
    }

    public double[] findConversionRates(String[][] rates, String[][] queries) {
        Map<String, List<Edge>> graph = buildGraph(rates);

        double[] results = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            results[i] = bfs(graph, queries[i][0], queries[i][1]);
        }
        return results;
    }

    private Map<String, List<Edge>> buildGraph(String[][] rates) {
        Map<String, List<Edge>> graph = new HashMap<>();

        for (String[] rate : rates) {
            String from = rate[0];
            String to = rate[1];
            double rateValue = Double.parseDouble(rate[2]);

            // A → B
            graph.computeIfAbsent(from, k -> new ArrayList<>())
                    .add(new Edge(to, rateValue));

            // B → A (reverse)
            graph.computeIfAbsent(to, k -> new ArrayList<>())
                    .add(new Edge(from, 1.0 / rateValue));
        }

        return graph;
    }

    // ─── BFS
    private double bfs(Map<String, List<Edge>> graph, String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) return -1.0;
        if (start.equals(end)) return 1.0;

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new State(start, 1.0));
        visited.add(start);

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            for (Edge edge : graph.get(curr.currency)) {
                if (edge.to.equals(end)) {
                    return curr.productSoFar * edge.rate; // ✅ found!
                }

                if (!visited.contains(edge.to)) {
                    visited.add(edge.to);
                    queue.offer(new State(edge.to, curr.productSoFar * edge.rate));
                }
            }
        }

        return -1.0;
    }

}

class Edge {
    String to;
    double rate;

    Edge(String to, double rate) {
        this.to = to;
        this.rate = rate;
    }
}

class State {
    String currency;
    double productSoFar;

    State(String currency, double productSoFar) {
        this.currency = currency;
        this.productSoFar = productSoFar;
    }
}