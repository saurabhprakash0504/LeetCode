package com.tree;

public class LowestCommonAncestor3 {

    public static void main(String[] args) {
        NodeTemp root = new NodeTemp(1);

        root.left = new NodeTemp(2);
        root.left.parent = root;

        root.right = new NodeTemp(3);
        root.right.parent = root;

        root.left.left = new NodeTemp(4);
        root.left.left.parent = root.left;

        root.left.right = new NodeTemp(5);
        root.left.right.parent = root.left;

        root.right.left = new NodeTemp(6);
        root.right.left.parent = root.right;

        root.right.right = new NodeTemp(7);
        root.right.right.parent = root.right;

        NodeTemp res = new LowestCommonAncestor3()
                .lowestCommonAncestor(root.right.left, root.left.right);

        System.out.println(res.val);
    }

    public NodeTemp lowestCommonAncestor(NodeTemp a, NodeTemp b) {
        NodeTemp p = a;
        NodeTemp q = b;
        System.out.println("p " + p.val + " q " + q.val);
        while (p != q) {
            p = p.parent == null ? b : p.parent;
            q = q.parent == null ? a : q.parent;
        }
        return p;
    }
}


class NodeTemp {
    public int val;
    public NodeTemp left;
    public NodeTemp right;
    public NodeTemp parent;

    public NodeTemp(int val) {
        this.val = val;
    }
}