package com.stack;

import java.util.*;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public static void main(String[] args) {

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        while (r < nums.length && r < k) {
            while (!dq.isEmpty() && nums[r] > nums[dq.peekFirst()]) {
                dq.pollFirst();
            }

            dq.addLast(r);

            r++;
        }

        res[l] = nums[dq.peekFirst()];

        l++;

        while (r < nums.length) {
            while (!dq.isEmpty() && nums[r] > nums[dq.peekFirst()]) {
                dq.pollFirst();
            }
            dq.addLast(r);
            res[l] = nums[dq.peekFirst()];
            l++;
            r++;
        }

        return res;


    }
}
