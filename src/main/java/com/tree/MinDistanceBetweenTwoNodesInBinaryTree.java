package com.tree;

//YT - APNA COLLEGE
// CODE WITH THE HELP OF COPILOT

public class MinDistanceBetweenTwoNodesInBinaryTree {


    public static void main(String[] args) {


    }

    public int findDist(Node root, int a, int b) {
        // code here
        Node lca = findLCA(root, a, b);
        //  System.out.println("lca "+ lca.data);
        int d1 = findDis(lca, a);
        int d2 = findDis(lca, b);
        return d1 + d2;
    }

    Node findLCA(Node root, int a, int b) {
        if (root == null) {
            return root;
        }

        if (root.data == a || root.data == b) {
            return root;
        }

        Node left = findLCA(root.left, a, b);
        Node right = findLCA(root.right, a, b);
        if (left != null && right != null) {
            return root;
        } else if (left == null && right != null) {
            return right;
        } else {
            return left;
        }

    }


    int findDis(Node root, int a) {
        if (root == null) {
            return -1;
        }

        if (root.data == a) {
            return 0;
        }

        int left = findDis(root.left, a);
        int right = findDis(root.right, a);


        if (left == -1 && right == -1) {
            return -1;
        }

        if (left == -1) {
            return right + 1;

        } else {

            return left + 1;
        }

    }

}
