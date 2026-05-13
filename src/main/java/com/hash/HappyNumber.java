package com.hash;

import java.util.HashSet;

public class HappyNumber {

    public static void main(String[] args) {
        int n = 19;
        HappyNumber obj = new HappyNumber();
        System.out.println(obj.isHappy(n));
    }

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        boolean res = true;
        while (true) {

            int sum = 0;
            //   System.out.println("n >> "+ n);
            while (n != 0) {
                int digit = n % 10;
                sum = sum + (digit * digit);
                n = n / 10;
            }

            n = sum;

            if (sum == 1) {
                res = true;
                break;
            }

            if (set.contains(sum)) {
                res = false;
                break;
            }
            set.add(sum);
        }

        return res;
    }
}
