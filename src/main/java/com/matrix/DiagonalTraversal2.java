package com.matrix;

import java.util.*;

public class DiagonalTraversal2 {

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(1, 2, 3));
        nums.add(Arrays.asList(4, 5, 6));
        nums.add(Arrays.asList(7, 8, 9));

        int[] res = new DiagonalTraversal2().findDiagonalOrder(nums);
        System.out.println("res >> " + Arrays.toString(res));
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();


        int totalEle = 0;
        for (int r = nums.size() - 1; r >= 0; r--) {
            totalEle = totalEle + nums.get(r).size();
            for (int c = 0; c < nums.get(r).size(); c++) {
                int sum = r + c;
                map.putIfAbsent(sum, new ArrayList<Integer>());
                ArrayList<Integer> l = map.get(sum);
                l.add(nums.get(r).get(c));

            }
        }

        int[] res = new int[totalEle];

        //    int max = nums.size() + nums.get(0).size();
        int j = 0;
        for (int i = 0; i < totalEle; i++) {
            ArrayList<Integer> list = map.get(i);
            if (list != null) {
                for (int ii : list) {
                    res[j] = ii;
                    j++;
                }
            }
        }

        return res;

    }

    // USING BFS
    public int[] findDiagonalOrder1(List<List<Integer>> nums) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] val = q.poll();
            res.add(nums.get(val[0]).get(val[1]));
            if (val[1] == 0) {
                if (val[0] + 1 < nums.size() && val[1] < nums.get(val[0]).size()) {
                    q.add(new int[]{val[0] + 1, val[1]});
                }

            }
            if (val[1] + 1 < nums.get(val[0]).size()) {
                q.add(new int[]{val[0], val[1] + 1});
            }

        }

        int[] res1 = new int[res.size()];
        int j = 0;
        for (int i : res) {
            res1[j] = i;
            j++;
        }
        return res1;
    }
}
