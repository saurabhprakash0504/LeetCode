package com.tree;

public class SumRootToLeafNode {

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        SumRootToLeafNode obj = new SumRootToLeafNode();
        System.out.println(obj.sumNumbers(tree));
    }

    public int sumNumbers(Node root) {
        return dfs(root, 0);
    }

    int dfs(Node root, int sum) {
        if (root == null) {
            return 0;
        }

        sum = sum * 10 + root.data;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int left = dfs(root.left, sum);
        int right = dfs(root.right, sum);

        return left + right;
    }
}
