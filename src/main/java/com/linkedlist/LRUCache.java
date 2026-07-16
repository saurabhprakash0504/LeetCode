package com.linkedlist;

import java.util.HashMap;

public class LRUCache {
    static HashMap<Integer, Node3> map;
    static DoublyLinkedList doublyLinkedList;
    static int sizeRem;

    //TODO:: This code add the new node in front and remove the old node from back.
    LRUCache(int cap) {
        sizeRem = cap;
        map = new HashMap<Integer, Node3>();
        doublyLinkedList = new DoublyLinkedList();
    }

    public static int get(int key) {
        if (map.containsKey(key)) {
            Node3 node = map.get(key);
            int val = node.value;
            doublyLinkedList.deleteNode(node);
            Node3 temp = doublyLinkedList.addNodeAtStart(key, val);
            map.put(key, temp);
            return val;
        } else {
            return -1;
        }

    }

    public static void put(int key, int value) {
        if (map.containsKey(key)) {
            Node3 node = map.get(key);
            int val = node.value;
            doublyLinkedList.deleteNode(node);
            map.remove(key);
            Node3 temp = doublyLinkedList.addNodeAtStart(key, value);
            map.put(key, temp);
        } else if (sizeRem == 0) {
            Node3 last = doublyLinkedList.tail.prev;
            doublyLinkedList.deleteNode(last);
            map.remove(last.key);
            Node3 temp = doublyLinkedList.addNodeAtStart(key, value);
            map.put(key, temp);
        } else if (sizeRem > 0) {
            Node3 temp = doublyLinkedList.addNodeAtStart(key, value);
            map.put(key, temp);
            sizeRem--;
        }
    }
}


class DoublyLinkedList {

    static Node3 head;
    static Node3 tail;

    DoublyLinkedList() {
        head = new Node3(-1, -1);
        tail = new Node3(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    static Node3 addNodeAtStart(int key, int val) {

        Node3 temp = new Node3(key, val);
        Node3 nextHeadNode = head.next;
        temp.prev = head;
        head.next = temp;
        temp.next = nextHeadNode;
        nextHeadNode.prev = temp;
        return temp;
    }


    static void deleteNode(Node3 node) {
        Node3 prevOldNode = node.prev;
        Node3 nextOldNode = node.next;
        prevOldNode.next = nextOldNode;
        nextOldNode.prev = prevOldNode;
    }

}

class Node3 {

    int key;
    int value;
    Node3 next;
    Node3 prev;

    Node3(int k, int v) {

        key = k;
        value = v;
        next = null;
        prev = null;
    }
}