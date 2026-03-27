package com.array;

import java.util.Arrays;

public class MaximumSwap {

    public static void main(String[] args) {
        int num = 2736;
        MaximumSwap obj = new MaximumSwap();
        int res = obj.maximumSwap(num);
        System.out.println(res);
    }

    public int maximumSwap(int num) {

        int[] arr = new int[10];
        Arrays.fill(arr, -1);
        String s = String.valueOf(num);
        for(int i=0;i<s.length();i++){
            int temp = s.charAt(i) - '0';
            arr[temp] = i;
        }

      //  System.out.println("arr >> "+ Arrays.toString(arr));
        boolean isFound = false;
        for(int i=0;i<s.length();i++){
            int temp = s.charAt(i) - '0';
            for(int j=9;j>temp;j--){
                if(arr[j] > i){
                    String te=  swap(s, i, arr[j]);
                    return Integer.parseInt(te);
                }
            }

        }
        return Integer.parseInt(s);
    }


    String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }
}
