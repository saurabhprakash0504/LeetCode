package com.companyInterview.wise;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/*

    We have a web client that makes requests to Service B and Service C.
    If either service fails 3 times within 10 minutes, we should stop making requests to that service
    for 5 minutes (circuit breaker open).
    After 5 minutes, we should try again (circuit breaker half-open/closed).
    We need to implement this logic in the `WebClient.execute(Request)` method. (CIRCUIT BREAKER)

 */

abstract class Request {
    String host;

    public Request(String host) {
        this.host = host;
    }

    abstract Response call(); // This makes request to external server
}

class Response {
    int status;
    String body;
}

public class ActualCircuitBreaker {


    // Simulate time for testing
    private int simulatedMinute = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("=== Sample Input Test ===");
        runTest(new String[]{
                "3",
                "1,service_b/api,200",
                "2,service_b/api,200",
                "3,service_b/api,500"
        });

        System.out.println("\n=== Full Circuit Breaker Scenario ===");
        runTest(new String[]{
                "9",
                "1,service_b/api,200",   // success
                "2,service_b/api,500",   // failure 1
                "3,service_b/api,500",   // failure 2
                "4,service_b/api,500",   // failure 3 → circuit OPENS
                "5,service_b/api,200",   // BLOCKED (circuit open)
                "7,service_c/api,200",   // service_c unaffected
                "9,service_b/api,200",   // BLOCKED (only 5 min haven't passed since min 4)
                "9,service_c/api,500",   // service_c independent breaker
                "10,service_b/api,200"   // REOPENS (10 - 4 = 6 >= 5 min) → success
        });
    }

    // ─── Test Runner ─────────────────────────────────────────────────────────

    private static void runTest(String[] lines) throws Exception {
        ActualCircuitBreaker client = new ActualCircuitBreaker();

        int n = Integer.parseInt(lines[0].trim());

        for (int i = 1; i <= n; i++) {
            String[] parts = lines[i].split(",");
            int minute = Integer.parseInt(parts[0].trim());
            String host = parts[1].trim();
            int statusCode = Integer.parseInt(parts[2].trim());

            // Inject simulated time
            client.simulatedMinute = minute;

            // Build a fake Request that returns the prescribed status
            Request request = new Request(host) {
                @Override
                public Response call() {
                    Response r = new Response();
                    r.status = statusCode;
                    r.body = "body from " + host;
                    return r;
                }
            };

            Response response = client.execute(request);

            String result = response.status == 503
                    ? "BLOCKED  (circuit open)"
                    : "status=" + response.status;

            System.out.printf("min=%-3d  %-20s  →  %s%n", minute, host, result);
        }
    }


    private int getCurrentTimeInMinutes() {
        //return LocalDateTime.now().getMinute();
        //this is just for testing that I have used simulatedMinute
        return simulatedMinute;
    }

    //
    //FROM HERE: Candidate's code to implement Circuit Breaker pattern
    Map<String, CircuitBreaker> map = new ConcurrentHashMap<>();

    private CircuitBreaker getBreaker(String host) {
        return map.computeIfAbsent(host, h -> new CircuitBreaker());
    }

    //Write your code here
    //WE need to implement this logic in the `WebClient.execute(Request)` method. (CIRCUIT BREAKER)
    public Response execute(Request request) throws Exception {

        int now = getCurrentTimeInMinutes();
        CircuitBreaker breaker = getBreaker(request.host);
        if (breaker.isOpen(now)) {
            // Circuit is OPEN — block the call
            Response blocked = new Response();
            blocked.status = 503;
            blocked.body = "Circuit open for host: " + request.host;
            return blocked;
        }
        // Circuit is CLOSED — make the actual call
        try {
            Response response = request.call();
            if (response.status >= 500) {
                breaker.recordFailure(now);
            }
            return response;
        } catch (Exception e) {
            // Timeout or connection error — also a failure
            breaker.recordFailure(now);
            throw e;
        }
    }

}

class CircuitBreaker {

    private static final int FAILURE_THRESHOLD = 3;
    private static final int FAILURE_WINDOW_MINUTES = 10;
    private static final int OPEN_DURATION_MINUTES = 5;
    private final Queue<Integer> failureTimes = new LinkedList<>();
    private volatile Integer openedAtMinute = null;

    public synchronized boolean isOpen(int currentMinute) {
        if (openedAtMinute == null) return false;
        if (currentMinute - openedAtMinute > OPEN_DURATION_MINUTES) {
            openedAtMinute = null;
            failureTimes.clear();
            return false;
        }
        return true;
    }


    public synchronized void recordFailure(int currentMinute) {
        // Evict failures outside the 10-minute sliding window
        while (!failureTimes.isEmpty() &&
                currentMinute - failureTimes.peek() >= FAILURE_WINDOW_MINUTES) {
            failureTimes.poll();
        }

        failureTimes.offer(currentMinute);

        if (failureTimes.size() >= FAILURE_THRESHOLD) {
            openedAtMinute = currentMinute;
        }
    }
}
