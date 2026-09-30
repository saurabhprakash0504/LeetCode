package com.searching;

public class SearchInSortedRotatedArray {

    public static void main(String[] args) {
        SearchInSortedRotatedArray obj = new SearchInSortedRotatedArray();
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int key = 4;
        System.out.println(obj.search(arr, key));
    }

    int search(int[] arr, int key) {

        int pivot = findPivot(arr);

        if (key >= arr[pivot] && key <= arr[arr.length - 1]) {
            return findInd(arr, pivot, arr.length - 1, key);
        } else {
            return findInd(arr, 0, pivot - 1, key);
        }
    }

    int findPivot(int[] arr) {

        int s = 0;
        int e = arr.length - 1;

        while (s < e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] >= arr[0]) {
                s = mid + 1;
            } else {
                e = mid;
            }


        }
        return s;
    }

    int findInd(int[] arr, int s, int e, int key) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return -1;
    }
}
