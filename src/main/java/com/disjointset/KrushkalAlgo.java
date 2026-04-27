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
            if (ds.findParent(t.u) != ds.findParent(t.v)) {
                res = res + k.wt;
                ds.findSize(t.u, t.v);
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


    int findParent(int i) {

        if (i == parent.get(i)) {
            return i;
        }

        int par = findParent(parent.get(i));

        parent.set(i, par);

        return parent.get(i);
    }


    void findSize(int u, int v) {

        int par_u = findParent(u);
        int par_v = findParent(v);

        int size_par_u = size.get(par_u);
        int size_par_v = size.get(par_v);

        if (par_u == par_v) {
            return;
        } else if (size_par_u > size_par_v) {
            size.set(par_v, size_par_u + size_par_v);
            parent.set(par_v, par_u);
        } else {
            size.set(par_u, size_par_v + size_par_u);
            parent.set(par_u, par_v);
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