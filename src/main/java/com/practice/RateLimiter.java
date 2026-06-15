package com.practice;


import java.util.*;

public class RateLimiter {

    public static void main(String[] args) {
        List<Integer> user = Arrays.asList(1, 1, 1, 2, 2, 1);
        List<Integer> time = Arrays.asList(1, 2, 3, 1, 2, 11);
        int k = 2;
        int t = 10;

        RateLimiter rateLimiter = new RateLimiter();

        List<Integer> output = rateLimiter.getRequests(user, time, k, t);
        System.out.println(output); // Expected: [1, 1, 0, 1, 1, 1]
    }

    public static List<Integer> getRequests(List<Integer> user, List<Integer> times, int k, int t) {
        List<Integer> result = new ArrayList<>();
        int n = user.size();
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        //    map.putIfAbsent()
        for (int i = 0; i < n; i++) {
            Integer userId = user.get(i);
            Integer time = times.get(i);
            map.putIfAbsent(userId, new LinkedList<Integer>());

            Deque<Integer> list = map.get(userId);
            while (!list.isEmpty() && list.peekFirst() <= time -t ) {
                list.pollFirst();
            }

            if (list.size() < k) {
                result.add(1);
                list.offerLast(time);
            } else {
                result.add(0);
            }
        }
        return result;
    }
}
