package com.disjointset;

import java.util.ArrayList;

public class NumberOfOperationsToMakeNetworkConnected {

    public static void main(String[] args) {
        NumberOfOperationsToMakeNetworkConnected sol = new NumberOfOperationsToMakeNetworkConnected();
        int n = 6;
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println(sol.makeConnected(n, connections));
    }

    public int makeConnected(int n, int[][] connections) {

        Disjoint3 ds = new Disjoint3(n);

        int extra = 0;
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];


            if (ds.findUltimateParent(u) == ds.findUltimateParent(v)) {
                extra++;
            } else {
                ds.updateSize(u, v);
            }
        }

        System.out.println("parent >> " + ds.parent);
        System.out.println("size >> " + ds.size);


        int component = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                component++;
            }
        }

        System.out.println("extra >> " + extra);
        System.out.println("component >> " + component);

        if (extra >= (component - 1)) {
            return component - 1;
        } else {
            return -1;
        }

    }
}

class Disjoint3 {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    Disjoint3(int n) {

        for (int i = 0; i <= n; i++) {
            parent.add(i, i);
            size.add(i, 1);
        }

    }

    int findUltimateParent(int u) {

        if (u == parent.get(u)) {
            return u;
        }

        int ulp_u = findUltimateParent(parent.get(u));
        parent.set(u, ulp_u);

        return parent.get(u);


    }

    //we update the size of parentU and parentV and not size of u and size of v
    void updateSize(int u, int v) {
        u = findUltimateParent(u);   // ensure u is ultimate parent
        v = findUltimateParent(v);   // ensure v is ultimate parent

        if (u == v) return;

        if (size.get(u) < size.get(v)) {
            parent.set(u, v);
            size.set(v, size.get(u) + size.get(v));
        } else {
            parent.set(v, u);
            size.set(u, size.get(u) + size.get(v));
        }
    }


}
