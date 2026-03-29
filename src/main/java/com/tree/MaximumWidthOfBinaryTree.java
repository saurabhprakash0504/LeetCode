package com.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        Node tree = new Node(3);
        tree.left = new Node(4);
        tree.right = new Node(5);
        tree.left.left = new Node(6);
        tree.left.right = new Node(7);
        tree.right.right = new Node(8);
        tree.right.right.left = new Node(9);
        tree.right.right.left.left = new Node(10);

        System.out.println(new MaximumWidthOfBinaryTree().widthOfBinaryTree(tree));
    }

    public int widthOfBinaryTree(Node root) {

        int max = Integer.MIN_VALUE;
        Queue<Node8> q = new LinkedList<>();
        q.add(new Node8(root, 0));
        q.add(null);

        int s = 0;
        int e = 0;
        int min = 0;
        while (q.size() > 1) {
            Node8 p = q.poll();
            if (p == null) {
                int curr = e - s + 1;
                max = Integer.max(max, curr);

                if (q.size() >= 1) {
                    min = q.peek().val;
                    s = q.peek().val - min;
                    e = q.peek().val - min;
                }
                q.add(null);
            } else {
                e = p.val - min;

                if (p.n.left != null) {
                    q.add(new Node8(p.n.left, 2 * p.val + 1));
                }

                if (p.n.right != null) {
                    q.add(new Node8(p.n.right, 2 * p.val + 2));
                }
            }


        }

        int curr = e - s + 1;
        max = Integer.max(max, curr);

        return max;


    }
}

class Node8 {
    Node n;
    int val;

    Node8(Node r, int w) {
        n = r;
        val = w;
    }
}
