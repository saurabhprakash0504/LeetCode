package com.stack;

import java.util.Arrays;

public class ImplementKStacksInAnArray {

    //YT - Love Babbar
    public static void main(String[] args) {

        kStacks ks = new kStacks(10, 3);
        ks.push(15, 2);
        ks.push(45, 2);
        System.out.println(ks.pop(2));
        System.out.println(ks.pop(2));
        System.out.println(ks.pop(2));
    }


}

class kStacks {

    // main array to store elements
    private int[] arr;

    int[] top;
    int[] next;
    int freeTop = 0;
    int maxi;

    public kStacks(int n, int k) {
        // initialize data structures for k stacks
        arr = new int[n];
        top = new int[k];
        next = new int[n];
        Arrays.fill(top, -1);
        for (int i = 0; i < n; i++) {
            next[i] = i + 1;
        }
        next[n - 1] = -1;
        maxi = n;
    }

    public void push(int x, int i) {
        // push element x into stack i
        if (freeTop == maxi) {
            return;
        }

        int ind = freeTop;
        freeTop = next[ind];
        arr[ind] = x;
        next[ind] = top[i];
        top[i] = ind;

    }

    public int pop(int i) {
        // pop element from stack i
        //  System.out.println("arrays "+ Arrays.toString(arr));
        //    System.out.println("top  "+ Arrays.toString(top));
        if (top[i] == -1) {
            return -1;
        }

        int ind = top[i];
        top[i] = next[ind];
        next[ind] = freeTop;
        freeTop = ind;

        return arr[ind];
    }
}
