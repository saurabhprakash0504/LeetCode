package com.sweepLine;

import java.util.*;

public class CorporateFlightBookings {

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        CorporateFlightBookings obj = new CorporateFlightBookings();
        int[] result = obj.corpFlightBookings(bookings, n);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        ArrayList<Temp6> al = new ArrayList<>();

        int[] arr = new int[n + 2];
        for (int i = 0; i < bookings.length; i++) {
            al.add(new Temp6(bookings[i][0], bookings[i][2]));
            al.add(new Temp6(bookings[i][1] + 1, -bookings[i][2]));

        }
        //  Collections.sort(al, (a1, a2) -> a1.ind- a2.ind);
        for (int i = 0; i < al.size(); i++) {
            Temp6 t = al.get(i);
            arr[t.ind] = arr[t.ind] + t.val;
        }

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            if (i != 0)
                res[i] = arr[i + 1] + res[i - 1];
            else
                res[i] = arr[i + 1];
        }

        return res;
    }
}

class Temp6 {
    int ind;
    int val;

    Temp6(int i, int v) {
        ind = i;
        val = v;
    }
}