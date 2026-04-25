package com.disjointset;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(i, 1);
        }
    }

    //Path Compression and finding the ultimate parent
    int findUltimateParent(int n) {
        if (n == parent.get(n)) {
            return n;
        }

        int ultimateParent = findUltimateParent(parent.get(n));
        parent.set(n, ultimateParent);

        return parent.get(n);

    }

    //Union By rank
    void unionByRank(int u, int v) {

        int parentU = findUltimateParent(u);
        int parentV = findUltimateParent(v);

        int rank_ParentU = rank.get(parentU);
        int rank_ParentV = rank.get(parentV);

        if (parentU == parentV) {
            return;
        } else if (rank_ParentU > rank_ParentV) {
            parent.set(parentV, parentU);
        } else if (rank_ParentV > rank_ParentU) {
            parent.set(parentU, parentV);
        } else {
            parent.set(parentV, parentU);
            rank.set(parentU, rank_ParentU + 1);

        }

    }


    //Union By rank
    void unionBySize(int u, int v) {

        int parentU = findUltimateParent(u);
        int parentV = findUltimateParent(v);

        int size_ParentU = size.get(parentU);
        int size_ParentV = size.get(parentV);

        if (parentU == parentV) {
            return;
        } else if (size_ParentU > size_ParentV) {
            parent.set(parentV, parentU);
            size.set(parentU, size_ParentU + size_ParentV);
        } else {
            parent.set(parentU, parentV);
            size.set(parentV, size_ParentV + size_ParentU);

        }

    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        if (ds.findUltimateParent(3) == ds.findUltimateParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

         ds.unionByRank(3, 7);

        if (ds.findUltimateParent(3) == ds.findUltimateParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }

}
