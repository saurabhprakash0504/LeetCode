package com.companyInterview.wise;

import java.util.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class RateLimiter2 {

    private final Map<Integer, Deque<Integer>> userHistory = new ConcurrentHashMap<>();
    private final Map<Integer, ReentrantLock> userLocks = new ConcurrentHashMap<>();

    public List<Integer> getRequests(List<Integer> user, List<Integer> time, int k, int t) {
        int n = user.size();
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int idx = i;
            final int currUserId = user.get(idx);
            final int currTime = time.get(idx);

            Thread thread = new Thread(() -> {
                // Get or create a per-user lock
                userLocks.putIfAbsent(currUserId, new ReentrantLock());
                ReentrantLock lock = userLocks.get(currUserId);

                lock.lock();
                try {
                    userHistory.putIfAbsent(currUserId, new ArrayDeque<>());
                    Deque<Integer> history = userHistory.get(currUserId);

                    // Evict timestamps outside the window
                    while (!history.isEmpty() && history.peekFirst() <= currTime - t) {
                        history.pollFirst();
                    }

                    if (history.size() < k) {
                        result.set(idx, 1);
                        history.addLast(currTime);
                    }
                    // Blocked: result[idx] stays 0, history unchanged

                } finally {
                    lock.unlock();
                }
            });

            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> user = Arrays.asList(1, 1, 1, 2, 2, 1);
        List<Integer> time = Arrays.asList(1, 2, 3, 1, 2, 11);
        int k = 2;
        int t = 10;

        RateLimiter2 rateLimiter = new RateLimiter2();

        List<Integer> output = rateLimiter.getRequests(user, time, k, t);
        System.out.println(output); // Expected: [1, 1, 0, 1, 1, 1]
    }
}