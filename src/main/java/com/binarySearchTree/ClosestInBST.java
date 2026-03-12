package com.binarySearchTree;

public class ClosestInBST {

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        int k = 7;
        System.out.println(minDiff(root, k));
    }


    static int minDiff(Node root, int k) {

        if (root == null) {
            return Integer.MAX_VALUE;
        }

        int diff = (int) Math.abs(root.data - k);

        if (k < root.data) {
            return Integer.min(diff, minDiff(root.left, k));
        } else if (k > root.data) {
            return Integer.min(diff, minDiff(root.right, k));
        } else {
            return 0;
        }

    }
}
