package com.binarySearchTree;

//YT - TakeUForward
public class KokoEatingBananas {

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(minEatingSpeed(piles, h));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int p : piles) {
            max = Integer.max(p, max);
        }
        System.out.println("max >> " + max);

        int low = 1;
        int high = max;
        int res = -1;
        while (low <= high) {
            int mid = (high + low) / 2;

            boolean val = isFeasible(mid, piles, h);
            // System.out.println("mid >> "+ mid + " feasible "+ val);
            if (val) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return res;
    }

    static boolean isFeasible(int speed, int[] piles, int h) {
        double val = 0;
        for (int i = 0; i < piles.length; i++) {
            int pile = piles[i];
            val = val + Math.ceil(pile / (speed * 1.0));
        }
        System.out.println("speed " + speed + "val " + val + " h " + h);
        if (val <= h) {
            return true;
        } else {
            return false;
        }
    }


}
