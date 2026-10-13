package com.sweepLine;

import java.util.*;

public class CarPooling {

    public static void main(String[] args) {

        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        int capacity = 4;
        CarPooling obj = new CarPooling();
        boolean result = obj.carPooling(trips, capacity);
        System.out.println(result); // Output: false
    }

    public boolean carPooling(int[][] trips, int capacity) {
        ArrayList<Temp4> al = new ArrayList<>();
        for (int i = 0; i < trips.length; i++) {
            al.add(new Temp4(trips[i][1], trips[i][0]));
            al.add(new Temp4(trips[i][2], -trips[i][0]));
        }
        Collections.sort(al, (a1, a2) -> {
            int val = Integer.compare(a1.ind, a2.ind);
            if (val == 0) {
                return Integer.compare(a1.delta, a2.delta);
            }

            return val;
        });

        int curr = 0;
        for (int i = 0; i < al.size(); i++) {
            curr = curr + al.get(i).delta;
            if (curr > capacity) {
                return false;
            }
        }

        return true;
    }
}

