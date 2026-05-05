package com.disjointset;

import java.util.ArrayList;
import java.util.Collections;

public class KrushkalAlgo {

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {{0, 1, 10}, {0, 2, 6}, {0, 3, 5}, {1, 3, 15}, {2, 3, 4}};

        System.out.println(kruskalsMST(V, edges));
    }

    static int kruskalsMST(int V, int[][] edges) {
        // code here
        ArrayList<Krus> al = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {

            al.add(new Krus(edges[i][2], new Temp(edges[i][0], edges[i][1])));
        }

        Collections.sort(al, (a, b) -> a.wt - b.wt);

        Disjoint ds = new Disjoint(V);

        int res = 0;
        for (Krus k : al) {
            Temp t = k.temp;
            if (ds.findUltimateParent(t.u) != ds.findUltimateParent(t.v)) {
                res = res + k.wt;
                ds.unionBySize(t.u, t.v);
            }
        }

        return res;
    }
}


class Disjoint {

    ArrayList<Integer> size = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();

    Disjoint(int v) {

        for (int i = 0; i <= v; i++) {
            size.add(i, 1);
            parent.add(i, i);
        }
    }



    int findUltimateParent(int u){

        if(u == parent.get(u)){
            return u;
        }

        int p = findUltimateParent(parent.get(u));
        parent.set(u, p);

        return parent.get(u);


    }

    void unionBySize(int u, int v){

        int parent_u = findUltimateParent(u);
        int parent_v = findUltimateParent(v);

        int size_parent_u = size.get(parent_u);
        int size_parent_v = size.get(parent_v);

        if(parent_u == parent_v){
            return;
        }else if(size_parent_u < size_parent_v){
            parent.set(parent_u, parent_v);
            size.set(parent_v, parent_v+parent_u);
        }else {
            parent.set(parent_v, parent_u);
            size.set(parent_u, parent_v+parent_u);
        }
    }

}

class Krus {

    int wt;
    Temp temp;

    Krus(int w, Temp t) {

        wt = w;
        temp = t;

    }
}

class Temp {

    int u;
    int v;

    Temp(int uu, int vv) {
        u = uu;
        v = vv;
    }
}