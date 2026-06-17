package com.practice;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter2 {

    ConcurrentHashMap<String, Long> map;

    RateLimiter2() {
        map = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        RateLimiter2 rateLimiter = new RateLimiter2();
        System.out.println(rateLimiter.firstEasyImplementation(1, "foo")); // true
        System.out.println(rateLimiter.firstEasyImplementation(2, "bar")); // true
        System.out.println(rateLimiter.firstEasyImplementation(3, "foo")); // false
        System.out.println(rateLimiter.firstEasyImplementation(8, "bar")); // false
        System.out.println(rateLimiter.firstEasyImplementation(10, "foo")); // false
        System.out.println(rateLimiter.firstEasyImplementation(11, "foo")); // true
    }


     boolean firstEasyImplementation(long timestamp, String message) {

        if (map.containsKey(message)) {
            Long previousTime = map.get(message);

            if (timestamp - previousTime < 10) {
                return false;
            } else {
                map.put(message, timestamp);
                return true;
            }
        } else {
            map.put(message, timestamp);
            return true;
        }

    }

}
