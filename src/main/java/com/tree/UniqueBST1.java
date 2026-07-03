package com.tree;

public class UniqueBST1 {

    public static void main(String[] args) {

    }

    public int numTrees(int n) {
        return find(n);
    }

    int find(int n){
        if(n ==0 || n ==1 ){
            return 1;
        }

        int sum = 0;
        for(int i=1;i<=n;i++){
            int left = find(i-1);
            int right = find(n-i);
            sum = sum + (left * right);
        }

        return sum;
    }
}
