package com.practice;


import java.time.LocalDateTime;
import java.util.*;
import java.util.Queue;

public class Wise {

    public static void main(String[] args) {

    }

}


abstract class Request {
    String host;

    abstract Response call(); // This makes request to external server
}

class Response {
    int status;
    String body;
}

class WebClient {


    private int getCurrentTimeInMinutes() {
        return LocalDateTime.now().getMinute();
    }

    //
    //FROM HERE: Candidate's code to implement Circuit Breaker pattern
    Map<String, CircuitBreaker> map = new HashMap<>();

    private CircuitBreaker getBreaker(String host) {
        return map.computeIfAbsent(host, h -> new CircuitBreaker());
    }

    // THIS IS THE METHOD CANDIDATE NEEDS TO IMPLEMENT


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

        Response response = request.call();


        if (response.status == 500 /* or timeout */) {

            breaker.recordFailure(now);

        }


        return response;

    }

}

class CircuitBreaker {

    private static final int FAILURE_THRESHOLD = 3;

    private static final int FAILURE_WINDOW_MINUTES = 10;

    private static final int OPEN_DURATION_MINUTES = 5;


    private final Queue<Integer> failureTimes = new LinkedList<>();

    private Integer openedAtMinute = null;


    public boolean isOpen(int currentMinute) {

        if (openedAtMinute == null) return false;


        if (currentMinute - openedAtMinute >= OPEN_DURATION_MINUTES) {

            openedAtMinute = null;

            failureTimes.clear();

            return false;

        }

        return true;

    }


    public void recordFailure(int currentMinute) {

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
