package com.binarySearchTree;

public class ValidateBinarySearchTree {

    public static void main(String[] args) {

    }

    public boolean isValidBST(Node root) {

        return find(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean find(Node root, Long min, Long max) {

        if (root == null) {
            return true;
        }

        if (root.data >= max || root.data <= min) {
            return false;
        }

        return find(root.left, min, Long.valueOf(root.data)) && find(root.right, Long.valueOf(root.data), max);
    }
}
