package com.sweepLine;
//YT - https://www.youtube.com/watch?v=zcMYtmwQNMg
import java.util.*;

public class MyCalendarIII {

    public static void main(String[] args) {
        MyCalendarThree obj = new MyCalendarThree();
        System.out.println(obj.book(10, 20)); // Output: 1
        System.out.println(obj.book(50, 60)); // Output: 1
        System.out.println(obj.book(10, 40)); // Output: 2
        System.out.println(obj.book(5, 15));  // Output: 3
        System.out.println(obj.book(5, 10));  // Output: 3
        System.out.println(obj.book(25, 55)); // Output: 3
    }

}

class MyCalendarThree {

    ArrayList<Temp4> al;

    public MyCalendarThree() {
        al = new ArrayList<>();
    }

    public int book(int startTime, int endTime) {
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

            //   al.remove(start);
            //  al.remove(end);

        }


        return max;
    }
}

