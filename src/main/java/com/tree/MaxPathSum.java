package com.tree;

public class MaxPathSum {

    public static void main(String[] args) {
        MaxPathSum obj = new MaxPathSum();
        Node root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        System.out.println(obj.maxPathSum(root));
    }

    public int maxPathSum(Node root) {


        find(root);

        return maxi;
    }

    int maxi = Integer.MIN_VALUE;

    int find(Node root) {
        if (root == null) {
            return 0;
        }

        int left = find(root.left);

        int right = find(root.right);

        int total = left + right + root.data;
        int leftT = left + root.data;
        int rightT = right + root.data;
        int justT = root.data;

        maxi = Integer.max(maxi, Integer.max(total, Integer.max(leftT, Integer.max(rightT, justT))));

        if (justT > leftT && justT > rightT) {
            return justT;
        } else if (leftT > rightT) {
            return leftT;
        } else {
            return rightT;
        }

    }
}
