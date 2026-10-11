package com.tree;

import java.util.*;

public class BinaryTreePaths {

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(5);

        List<String> result = binaryTreePaths.binaryTreePaths(root);
        System.out.println(result); // Output: ["1->2->5", "1->3"]
    }

    public List<String> binaryTreePaths(Node root) {
        List<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(Node node, String path, List<String> result) {
        if (node == null) {
            return;
        }

        path += node.data;

        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        dfs(node.left, path + "->", result);
        dfs(node.right, path + "->", result);
    }

}
