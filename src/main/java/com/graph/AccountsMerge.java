package com.graph;

import java.util.*;
import java.util.List;
import java.util.Set;

public class AccountsMerge {

    public static void main(String[] args) {
        AccountsMerge am = new AccountsMerge();
        List<List<String>> accounts = new ArrayList<>();
        //accounts.add(Arrays.asList("John", "
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Build graph
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            String firstEmail = acc.get(1);

            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                emailToName.put(email, name);

                graph.putIfAbsent(email, new ArrayList<>());
                graph.putIfAbsent(firstEmail, new ArrayList<>());

                // Connect first email with current email
                graph.get(firstEmail).add(email);
                graph.get(email).add(firstEmail);
            }
        }

        // DFS
        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> component = new ArrayList<>();
                dfs(email, graph, visited, component);

                Collections.sort(component);
                component.add(0, emailToName.get(email));
                result.add(component);
            }
        }

        return result;
    }

    private void dfs(String email, Map<String, List<String>> graph,
                     Set<String> visited, List<String> component) {
        visited.add(email);
        component.add(email);

        for (String nei : graph.get(email)) {
            if (!visited.contains(nei)) {
                dfs(nei, graph, visited, component);
            }
        }
    }
}
