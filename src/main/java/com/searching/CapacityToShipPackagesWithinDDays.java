package com.searching;

import java.util.ArrayList;

public class CapacityToShipPackagesWithinDDays {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        CapacityToShipPackagesWithinDDays obj = new CapacityToShipPackagesWithinDDays();
        int res = obj.leastWeightCapacity(arr, 2);
        System.out.println(res);
    }

    public int leastWeightCapacity(ArrayList<Integer> arr, int d) {
        int l = 1;
        int r = 0;
        for (int a : arr) {
            r = r + a;
        }

        int ans = r;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (isPossible(arr, m, d)) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }

    boolean isPossible(ArrayList<Integer> arr, int m, int d) {
        // System.out.println("m >> "+ m);
        int wt = 0;
        int days = 1;
        for (int i = 0; i < arr.size(); i++) {
            int val = arr.get(i);
            int temp = wt + val;
            if (temp <= m) {
                wt = wt + val;
            } else {
                days++;
                if (days > d || val > m) {
                    // System.out.println("false >>");
                    return false;
                }
                wt = val;
            }
        }
        //   System.out.println("true >>");
        return true;
    }
}
