package com.graph;

import java.util.*;

public class MinCostToConnectAllPoints {

    public static void main(String[] args) {

        MinCostToConnectAllPoints obj = new MinCostToConnectAllPoints();
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int result = obj.minCostConnectPoints(points);
        System.out.println("Minimum cost to connect all points: " + result);
    }

    public int minCostConnectPoints(int[][] points) {

        int V = points.length;

        ArrayList<Krus> al = new ArrayList<>();


        // build edges

        for (int i = 0; i < V; i++) {

            for (int j = i + 1; j < V; j++) {

                int dist = Math.abs(points[i][0] - points[j][0]) +

                        Math.abs(points[i][1] - points[j][1]);


                al.add(new Krus(dist, new Temp4(i, j)));

            }

        }

        return kruskalsMST(V, al);

    }

    int kruskalsMST(int V, ArrayList<Krus> al) {
        // code here
       /* ArrayList<Krus> al = new ArrayList<>();
        for(int i=0;i< edges.length;i++){

            al.add(new Krus(edges[i][2], new Temp(edges[i][0], edges[i][1])));
        }*/

        Collections.sort(al, (a, b) -> a.wt - b.wt);

        Disjoint ds = new Disjoint(V);

        int res = 0;
        for (Krus k : al) {
            Temp4 t = k.temp;
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
            size.set(par_u, size_par_u + size_par_v);
            parent.set(par_v, par_u);
        } else {
            size.set(par_v, size_par_v + size_par_u);
            parent.set(par_u, par_v);
        }


    }

}

class Krus {

    int wt;
    Temp4 temp;

    Krus(int w, Temp4 t) {

        wt = w;
        temp = t;

    }
}

class Temp4 {

    int u;
    int v;

    Temp4(int uu, int vv) {
        u = uu;
        v = vv;
    }
}


