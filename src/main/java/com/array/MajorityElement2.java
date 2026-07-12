package com.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MajorityElement2 {

    public static void main(String[] args) {
        MajorityElement2 me2 = new MajorityElement2();
        int arr[] = {1, 2, 3, 1, 1, 2, 2, 2};
        System.out.println(me2.findMajority(arr));
    }

    public List<Integer> findMajority(int[] arr) {

        List<Integer> finalList = majorityElement(arr);

        return finalList;
    }

    public List<Integer> majorityElement(int[] nums) {
        int first = 0, second = 0;
        int firstC = 0, secondC = 0;

        for (int n : nums) {
            if (n == first) {
                firstC++;
            } else if (n == second) {
                secondC++;
            } else if (firstC == 0) {
                first = n;
                firstC = 1;
            } else if (secondC == 0) {
                second = n;
                secondC = 1;
            } else {
                firstC--;
                secondC--;
            }
        }

        // verify
        firstC = 0;
        secondC = 0;
        for (int n : nums) {
            if (n == first) firstC++;
            else if (n == second) secondC++;
        }

        List<Integer> res = new ArrayList<>();
        int limit = nums.length / 3;

        if (firstC > limit) res.add(first);
        if (secondC > limit) res.add(second);

        return res;
    }
}
