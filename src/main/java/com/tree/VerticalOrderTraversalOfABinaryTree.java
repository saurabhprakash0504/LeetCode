package com.tree;

import java.util.*;

public class VerticalOrderTraversalOfABinaryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<List<Integer>> res = new VerticalOrderTraversalOfABinaryTree().verticalTraversal(root);
        for(List<Integer> l : res){
            System.out.println(l);
        }
    }

    public List<List<Integer>> verticalTraversal(Node root) {
        List<List<Integer>> finalList = new ArrayList<>();
        TreeMap<Integer,TreeMap<Integer,ArrayList<Integer>>> map = new TreeMap<>();

        find(root, map, 0, 0);
        for(Map.Entry<Integer,TreeMap<Integer,ArrayList<Integer>>> entry : map.entrySet()){
            int key = entry.getKey();
            TreeMap<Integer,ArrayList<Integer>> val = entry.getValue();
          /*  Collections.sort(val,
                        (o1, o2) -> o1.height.compareTo(o2.height));*/
            // Collections.sort(val);
            //  ArrayList<Integer> l = new ArrayList<>();
         /*  for(Node v : val){
                l.add(v.val);
           }*/
            ArrayList<Integer> l3 = new ArrayList<>();
            for(Map.Entry<Integer,ArrayList<Integer>> m : val.entrySet()){
                ArrayList<Integer> l2 = m.getValue();
                for(int ii : l2){
                    l3.add(ii);
                }

            }

            finalList.add(l3);
        }

        return finalList;
    }

    void find(Node root,  TreeMap<Integer,TreeMap<Integer,ArrayList<Integer>>> map , int vertical, int height){

        if(root == null){
            return;
        }

        if(map.containsKey(vertical)){
            TreeMap<Integer,ArrayList<Integer>> m1 = map.get(vertical);
            if(m1.containsKey(height)){
                ArrayList<Integer> temp = m1.get(height);
                temp.add(root.data);
                Collections.sort(temp);

            }else {
                TreeMap<Integer,ArrayList<Integer>> m12 = new TreeMap();
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(root.data);
                m1.put(height, temp);
                // map.put(vertical, m12);
            }
            //  list.add(new Node(height, root.val));
        }else {
            TreeMap<Integer,ArrayList<Integer>> m1 = new TreeMap();
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(root.data);
            m1.put(height, temp);
            map.put(vertical, m1);
        }

        find(root.left, map, vertical-1, height+1);
        find(root.right, map, vertical+1, height+1);

    }
}

class Node1 {

    int height;
    int val;

    Node1(int h, int v){
        height = h;
        val = v;
    }
}

