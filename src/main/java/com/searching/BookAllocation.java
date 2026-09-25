package com.searching;

public class BookAllocation {

    public static void main(String[] args) {
        BookAllocation obj = new BookAllocation();
        int[] arr = {12, 34, 67, 90};
        int k = 2;
        System.out.println(obj.findPages(arr, k));
    }

    public int findPages(int[] arr, int k) {
        // code here
        if (k > arr.length) {
            return -1;
        }

        //make sum as long as the value could be huge
        long sum = 0;

        for (int i : arr) {
            sum = sum + i;

        }

        long l = 0;
        long h = sum;
        long ans = -1;
        while (l <= h) {
            long m = l + (h - l) / 2;
            if (isValid(arr, k, m)) {
                ans = m;
                h = m - 1;
            } else {
                l = m + 1;
            }
        }

        return (int) ans;
    }

    boolean isValid(int[] arr, int k, long m) {

        int student = 1;
        int currVal = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = currVal + arr[i];
            if (temp <= m) {
                currVal = temp;
            } else {
                student++;
                if (student > k || arr[i] > m) {
                    return false;
                }
                currVal = arr[i];
            }
        }
        return true;
    }
}
