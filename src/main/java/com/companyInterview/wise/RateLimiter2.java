package com.companyInterview.wise;

import java.util.*;

class RateLimiter2 {
    public static List<Integer> getRequests(List<Integer> user, List<Integer> time, int k, int t) {
        int n = user.size();
        List<Integer> result = new ArrayList<>(n);
        Map<Integer, Deque<Integer>> userHistory = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int currUserId = user.get(i);
            int currTime = time.get(i);

            userHistory.putIfAbsent(currUserId, new ArrayDeque<>());
            Deque<Integer> history = userHistory.get(currUserId);

            // Evict timestamps outside the window [currTime - t, currTime]
            while (!history.isEmpty() && history.peekFirst() <= currTime - t) {
                history.pollFirst();
            }

            // Allow if strictly fewer than k requests in the window
            if (history.size() < k) {
                result.add(1);
                history.addLast(currTime);  // Only count allowed requests
            } else {
                result.add(0);
                // Blocked requests are NOT added to history
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> user = Arrays.asList(1, 1, 1, 2, 2, 1);
        List<Integer> time = Arrays.asList(1, 2, 3, 1, 2, 11);
        int k = 2;
        int t = 10;

        List<Integer> output = getRequests(user, time, k, t);
        System.out.println(output); // Expected: [1, 1, 0, 1, 1, 1]
    }
}