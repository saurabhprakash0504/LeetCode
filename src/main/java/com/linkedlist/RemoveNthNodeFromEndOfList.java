package com.linkedlist;

public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int n = 2;

        Node result = solution.removeNthFromEnd(head, n);

        // Print the modified list
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }

    public Node removeNthFromEnd(Node head, int n) {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        int i = 0;
        while (i < n) {
            fast = fast.next;
            i++;
        }

        // If fast is null → remove head
        if (fast == null) {
            return head.next;
        }

        // Move both pointers
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete the node
        slow.next = slow.next.next;

        return head;
    }
}
