package com.searching;

public class MedianOf2SortedArray {

    public static void main(String[] args) {
        MedianOf2SortedArray obj = new MedianOf2SortedArray();
        int[] a = {1, 3};
        int[] b = {2};
        System.out.println(obj.findMedianSortedArrays(a, b));
    }

    public double findMedianSortedArrays(int[] a, int[] b) {

        int n1 = a.length;
        int n2 = b.length;
        if (n1 > n2) return findMedianSortedArrays(b, a);

        int n = n1 + n2;
        int left = (n + 1) / 2;         // size of the left half
        int low = 0, high = n1;

        while (low <= high) {
            int mid1 = (low + high) >>> 1;                // elements taken from a for the left half
            int mid2 = left - mid1;                       // elements taken from b for the left half

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (mid1 < n1) r1 = a[mid1];
            if (mid2 < n2) r2 = b[mid2];
            if (mid1 - 1 >= 0) l1 = a[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = b[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) return Math.max(l1, l2);
                return ((long) Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0.0;
    }
}
