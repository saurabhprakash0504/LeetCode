package com.searching;

public class MaximumCandiesAllocatedToKChildren {

    public static void main(String[] args) {
        MaximumCandiesAllocatedToKChildren obj = new MaximumCandiesAllocatedToKChildren();
        int[] candies = {5, 8, 6};
        long k = 3;
        System.out.println(obj.maximumCandies(candies, k));
    }

    public int maximumCandies(int[] candies, long k) {
        int l = 1;
        int h = 0;
        long sum = 0;
        for (int i = 0; i < candies.length; i++) {
            h = Integer.max(h, candies[i]);
            sum = sum + candies[i];
        }
        if (sum < k) {
            return 0;
        } else if (sum == k) {
            return 1;
        }
        int ans = 0;
        while (l <= h) {
            int m = l + (h - l) / 2;
            boolean res = isPossible(candies, m, k);
            if (res) {
                ans = m;
                l = m + 1;
            } else {
                h = m - 1;
            }

        }
        return ans;
    }

    boolean isPossible(int[] candies, int m, long k) {
        long res = 0;

        for (int i = 0; i < candies.length; i++) {
            if (candies[i] >= m) {
                int val = candies[i] / m;
                res = res + val;
            }
        }
        if (res >= k) {
            return true;
        } else {
            return false;
        }
    }
}
