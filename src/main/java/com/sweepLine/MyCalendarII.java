package com.sweepLine;

import java.util.*;

public class MyCalendarII {

    public static void main(String[] args) {
        MyCalendarTwo obj = new MyCalendarTwo();
        System.out.println(obj.book(10, 20)); // returns true
        System.out.println(obj.book(50, 60)); // returns true
        System.out.println(obj.book(10, 40)); // returns true
        System.out.println(obj.book(5, 15)); // returns false
        System.out.println(obj.book(5, 10)); // returns true
        System.out.println(obj.book(25, 55)); // returns true
    }


}

class MyCalendarTwo {

    ArrayList<Temp4> al;

    public MyCalendarTwo() {
        al = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        Temp4 start = new Temp4(startTime, 1);
        Temp4 end = new Temp4(endTime, -1);
        al.add(start);
        al.add(end);
        Collections.sort(al, (a1, a2) -> {
            int res = Integer.compare(a1.ind, a2.ind);
            if (res != 0) {
                return res;
            } else {
                return Integer.compare(a1.delta, a2.delta);
            }
        });

        int curr = 0;
        int max = 0;
        for (int i = 0; i < al.size(); i++) {
            curr = curr + al.get(i).delta;
            max = Integer.max(curr, max);
            if (max >= 3) {
                al.remove(start);
                al.remove(end);
                return false;
            }

        }
        return true;
    }
}

class Temp4 {
    int ind;
    int delta;

    Temp4(int i, int d) {
        ind = i;
        delta = d;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */
