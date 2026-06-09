package com.tree;

public class MergeTree {

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.left = new Node(3);
        root1.right = new Node(2);
        root1.left.left = new Node(5);

        Node root2 = new Node(2);
        root2.left = new Node(1);
        root2.right = new Node(3);
        root2.left.right = new Node(4);
        root2.right.right = new Node(7);

        MergeTree obj = new MergeTree();
        Node res = obj.mergeTrees(root1, root2);
        System.out.println(res.data);
    }

    public Node mergeTrees(Node root1, Node root2) {


        //  Node root = new Node();
        return find(root1, root2);

        // return root;
    }

    Node find(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        int first = 0;
        int sec = 0;
        if (root1 != null) {
            first = root1.data;
        }

        if (root2 != null) {
            sec = root2.data;
        }

        Node root = new Node(first + sec);

        if (root1 != null && root2 != null) {
            root.left = find(root1.left, root2.left);
        } else if (root1 == null) {
            root.left = find(null, root2.left);
        } else if (root2 == null) {
            root.left = find(root1.left, null);
        }
        if (root1 != null && root2 != null) {
            root.right = find(root1.right, root2.right);
        } else if (root1 == null) {
            root.right = find(null, root2.right);
        } else if (root2 == null) {
            root.right = find(root1.right, null);
        }

        return root;
    }
}
