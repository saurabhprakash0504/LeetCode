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
        int d1 = dis(lca, a);
        int d2 = dis(lca, b);
        return d1 + d2;
    }

    Node findLCA(Node root, int a , int b){
        if(root == null){
            return root;
        }

        if(root.data == a || root.data == b){
            return root;
        }

        Node left = findLCA(root.left, a, b);
        Node right = findLCA(root.right, a,b);
        if(left != null && right != null){
            return root;
        }else if(left == null && right != null){
            return right;
        }else {
            return left;
        }

    }


    int dis(Node root, int target) {
        if (root == null) {
            return -1; // not found
        }

        if (root.data == target) {
            return 0; // found
        }

        int leftDistance = dis(root.left, target);

        if (leftDistance != -1) {
            return leftDistance + 1;
        }

        int rightDistance = dis(root.right, target);

        if (rightDistance != -1) {
            return rightDistance + 1;
        }

        return -1; // not found in either subtree
    }

}
