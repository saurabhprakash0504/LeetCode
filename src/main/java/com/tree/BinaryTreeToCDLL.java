package com.tree;

public class BinaryTreeToCDLL {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        //root.left.left = new Node(6);

        Node level = bTreeToClist(root);

        System.out.println("level >> "+ level);

    }
        // Function to convert binary tree into circular doubly linked list.
        static Node head = null;
        static Node prev = null;
        // Node curr = null;
        static Node bTreeToClist(Node root) {
            // your code here
            test(root);
            head.left= prev;
            prev.right=head;
            return head;
        }


        static void test(Node root){
            if(root == null){
                return ;
            }

            test(root.left);

            if(head == null){
                head = root;
                prev = root;
            }else{
                Node curr = root;
                prev.right = curr;
                curr.left = prev;
                prev = prev.right;
            }

            test(root.right);

        }

}
