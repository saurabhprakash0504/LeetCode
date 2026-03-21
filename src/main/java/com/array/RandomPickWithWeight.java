package com.array;

public class RandomPickWithWeight {

    public static void main(String[] args) {
        int[] w = {1, 3};
        RandomPickWithWeight obj = new RandomPickWithWeight(w);
        for (int i = 0; i < 10; i++) {
            System.out.println(obj.pickIndex());
        }
    }

    int[] wt;
    int sum = 0;

    public RandomPickWithWeight(int[] w) {
        wt = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            wt[i] = sum + w[i];
            sum = sum + w[i];
        }
    }

    public int pickIndex() {

        int randomNum = (int) ((Math.random() * sum) + 1);

        for (int i = 0; i < wt.length; i++) {
            if (randomNum <= wt[i]) {
                return i;
            }
        }

        return -1;

    }
}
