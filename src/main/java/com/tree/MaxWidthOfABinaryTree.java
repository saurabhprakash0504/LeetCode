package com.tree;

import java.util.HashMap;
import java.util.Map;

public class MaxWidthOfABinaryTree {

    public static void main(String[] args) {
        Node tree = new Node(3);
        tree.left = new Node(4);
        tree.right = new Node(5);
        tree.left.left = new Node(6);
        tree.left.right = new Node(7);
        tree.right.right = new Node(8);
        tree.right.right.left = new Node(9);
        tree.right.right.left.left = new Node(10);

        maxWidth(tree, 0);
        int max = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            max = Integer.max(max,entry.getValue());
        }
        System.out.println("max "+max);

    }

        static HashMap<Integer, Integer> map = new HashMap<>();

        static void maxWidth(Node root, int level) {

            if (root == null) {
                return;
            }

            maxWidth(root.left, level + 1);
           // map.merge(level, 1, Integer::sum);
            maxWidth(root.right, level + 1);
            map.merge(level, 1, Integer::sum);

        }
}
