package com.binarySearchTree;

public class RangeSumOfBST {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(18);

        System.out.println(new RangeSumOfBST().rangeSumBST(root, 7, 15));
    }

    public int rangeSumBST(Node root, int low, int high) {

        if (root == null) {
            return 0;
        }

        if (root.data < low) {
            return rangeSumBST(root.right, low, high);
        }

        if (root.data > high) {
            return rangeSumBST(root.left, low, high);
        }

        return root.data + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);

    }
}
