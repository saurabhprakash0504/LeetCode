package com.tree;

import java.util.*;

public class UniqueBST2 {

    public static void main(String[] args) {

    }

    public List<Node> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return find(1, n);
    }

    List<Node> find(int start, int end) {
        List<Node> result = new ArrayList<>();

        if (start > end) {
            // empty subtree — must still add a null placeholder
            // so the outer loop can pair it with the other side
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<Node> leftTrees = find(start, i - 1);
            List<Node> rightTrees = find(i + 1, end);

            // pair every left shape with every right shape
            for (Node left : leftTrees) {
                for (Node right : rightTrees) {
                    Node root = new Node(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        return result;
    }
}
