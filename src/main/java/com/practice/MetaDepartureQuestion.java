package com.practice;

public class MetaDepartureQuestion {

    public static void main(String[] args) {
        int[] D = {10, 9, 18, 7, 12};
        int[] R = {14, 15, 7, 17, 13};

        System.out.println(new MetaDepartureQuestion().findMinValue(D, R));
    }

    int findMinValue(int[] departure, int[] arrival) {
        int minValue = Integer.MAX_VALUE;
        int minDeparture = departure[0];
        for (int i = 0; i < departure.length; i++) {
            minDeparture = Math.min(minDeparture, departure[i]);
            int value = arrival[i] + minDeparture;
            minValue = Math.min(minValue, value);
        }
        return minValue;
    }
}
