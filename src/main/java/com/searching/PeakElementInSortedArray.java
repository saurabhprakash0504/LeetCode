package com.searching;

public class PeakElementInSortedArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(peakElement(arr, arr.length));
    }

    static public int peakElement(int[] a, int n) {
        if (n == 1) return 0;
        if (a[0] >= a[1]) return 0;
        if (a[n - 1] >= a[n - 2]) return n - 1;

        for (int i = 1; i < n - 1; i++) {
            if (a[i] >= a[i - 1] && a[i] >= a[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}