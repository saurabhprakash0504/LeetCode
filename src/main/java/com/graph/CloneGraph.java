package com.graph;

import java.util.*;

public class CloneGraph {

    public static void main(String[] args) {
        Node6 node1 = new Node6(1, new ArrayList<>());
        Node6 node2 = new Node6(2, new ArrayList<>());
        Node6 node3 = new Node6(3, new ArrayList<>());
        Node6 node4 = new Node6(4, new ArrayList<>());

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph obj = new CloneGraph();
        Node6 cloneNode = obj.cloneGraph(node1);

        //print the cloned graph
        System.out.println("Cloned graph:");
        System.out.println("Node: " + cloneNode.val);
        for (Node6 neighbor : cloneNode.neighbors) {
            System.out.println("Neighbor: " + neighbor.val);
        }

    }


    public Node6 cloneGraph(Node6 node) {
        if (node == null) {
            return node;
        }
        HashMap<Node6, Node6> map = new HashMap<Node6, Node6>();

        dfs(map, node);
        bfs(map, node);

        return map.get(node);

    }

    void dfs(HashMap<Node6, Node6> map, Node6 oldP) {
        if (map.containsKey(oldP)) {
            return;
        }

        Node6 newP = new Node6(oldP.val, new ArrayList<>());
        map.put(oldP, newP);

        for (Node6 n : oldP.neighbors) {
            dfs(map, n);
            newP.neighbors.add(map.get(n));
        }
    }


    void bfs(HashMap<Node6, Node6> map, Node6 node) {

        Queue<Node6> queue = new LinkedList<>();

        Node6 cloneStart = new Node6(node.val, new ArrayList<>());
        map.put(node, cloneStart);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node6 oldP = queue.poll();
            Node6 newP = map.get(oldP);

            for (Node6 nei : oldP.neighbors) {

                // if neighbor not cloned yet, clone + enqueue
                if (!map.containsKey(nei)) {
                    Node6 temp = new Node6(nei.val, new ArrayList<>());
                    map.put(nei, temp);
                    newP.neighbors.add(temp);
                    //Only here we are adding in the queue
                    queue.offer(nei);
                } else {
                    Node6 temp = map.get(nei);
                    newP.neighbors.add(temp);
                }

            }
        }
    }


  static class Node6 {
        public int val;
        public List<Node6> neighbors;

        public Node6() {
            val = 0;
            neighbors = new ArrayList<Node6>();
        }

        public Node6(int _val) {
            val = _val;
            neighbors = new ArrayList<Node6>();
        }

        public Node6(int _val, ArrayList<Node6> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

    }

}