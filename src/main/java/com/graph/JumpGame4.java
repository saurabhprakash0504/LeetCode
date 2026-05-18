package com.graph;

import java.util.*;

public class JumpGame4 {

    public static void main(String[] args) {
        JumpGame4 obj = new JumpGame4();
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println(obj.minJumps(arr));
    }

    public int minJumps(int[] arr) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                ArrayList<Integer> res = map.get(arr[i]);
                //  Collections.sort(res, Collections.reverseOrder());
                res.add(i);
            } else {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(i);
                map.put(arr[i], al);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[arr.length];
        q.add(0);
        int step = -1;
        vis[0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            int i = 1;
            step++;
            while (i <= size) {
                int ind = q.poll();

                if (ind == arr.length - 1) {
                    return step;
                }

                int prev = ind - 1;

                if (prev >= 0 && !vis[prev]) {
                    vis[prev] = true;
                    q.add(prev);
                }

                //next
                int next = ind + 1;

                if (next < arr.length && !vis[next]) {
                    vis[next] = true;
                    q.add(next);
                }

                // i and j
                if (map.containsKey(arr[ind])) {
                    ArrayList<Integer> al = map.get(arr[ind]);
                    for (int tem : al) {
                        if (!vis[tem]) {
                            vis[tem] = true;
                            q.add(tem);
                        }
                    }
                    map.remove(arr[ind]);
                }

                i++;

            }


        }

        return -1;

    }
}
