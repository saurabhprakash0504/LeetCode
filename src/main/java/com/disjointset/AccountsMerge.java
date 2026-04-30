package com.disjointset;

import java.util.*;

public class AccountsMerge {

    public static void main(String[] args) {
        AccountsMerge am = new AccountsMerge();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        List<List<String>> res = am.accountsMerge(accounts);
        for (List<String> account : res) {
            System.out.println(account);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, Integer> map = new HashMap<>();
        Disjoint5 ds = new Disjoint5(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            List<String> val = accounts.get(i);

            for (int j = 1; j < val.size(); j++) {
                if (map.containsKey(val.get(j))) {
                    int ind = map.get(val.get(j));
                    if (ds.findUltimateParent(ind) != ds.findUltimateParent(i)) {
                        ds.unionBySize(ind, i);
                    }
                } else {
                    map.put(val.get(j), i);
                }
            }
        }


        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, List<String>> temp = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue();

            int ulp = ds.findUltimateParent(val);
            //  String name = accounts.get(ulp).get(0);
            if (temp.containsKey(ulp)) {
                List<String> list = temp.get(ulp);
                list.add(key);
            } else {
                List<String> list = new ArrayList<>();
                list.add(key);
                temp.put(ulp, list);

            }

        }

        for (Map.Entry<Integer, List<String>> entry : temp.entrySet()) {
            int key = entry.getKey();
            List<String> val = entry.getValue();
            Collections.sort(val);
            String name = accounts.get(key).get(0);
            val.add(0, name);
            res.add(val);
        }


        return res;

    }
}

class Disjoint5 {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    Disjoint5(int n) {

        for (int i = 0; i <= n; i++) {
            parent.add(i, i);
            size.add(i, 1);
        }
    }

    int findUltimateParent(int u) {

        if (u == parent.get(u)) {
            return u;
        }

        int u_p = findUltimateParent(parent.get(u));

        parent.set(u, u_p);

        return parent.get(u);
    }


    void unionBySize(int u, int v) {

        int parent_u = findUltimateParent(u);
        int parent_v = findUltimateParent(v);

        int size_parent_u = size.get(parent_u);
        int size_parent_v = size.get(parent_v);

        if (parent_u == parent_v) {
            return;
        } else if (size_parent_u > size_parent_v) {
            size.set(parent_u, size_parent_u + size_parent_v);
            parent.set(parent_v, parent_u);
        } else {
            size.set(parent_v, size_parent_u + size_parent_v);
            parent.set(parent_u, parent_v);
        }
    }
}

