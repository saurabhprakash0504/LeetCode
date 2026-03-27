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
        Queue<Node8> q = new LinkedList<>();
        q.add(new Node8(root, 0));
        q.add(null);

        int max = 0;
        int start = -1;  // track first node index of current level
        int end = 0;

        while (q.size() > 1) {
            Node8 poll = q.poll();

            if (poll == null) {
                int curr = end - start + 1;
                max = Math.max(curr, max);

                start = -1; // reset for next level
                q.add(null);
            } else {
                if (start == -1) start = poll.wid; // first node of this level

                end = poll.wid;

                if (poll.root.left != null) {
                    int val = 2 * poll.wid + 1;
                    q.add(new Node8(poll.root.left, val));
                }

                if (poll.root.right != null) {
                    int val = 2 * poll.wid + 2;
                    q.add(new Node8(poll.root.right, val));
                }
            }
        }

        int curr = end - start + 1;
        max = Math.max(curr, max);
        return max;
    }
}

class Node8{
    Node root;
    int wid;

    Node8(Node r, int w){
        root = r;
        wid = w;
    }
}
