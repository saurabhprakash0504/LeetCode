package com.sweepLine;

import java.util.*;

public class MeetingRoom2 {

    public static void main(String[] args) {
        int[] start = {0, 5, 13, 24};
        int[] end = {2, 10, 23, 25};
        MeetingRoom2 obj = new MeetingRoom2();
        int result = obj.minMeetingRooms(start, end);
        System.out.println(result);
    }

    public int minMeetingRooms(int[] s, int[] e) {
        // code here
        ArrayList<Temp3> al = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            int start = s[i];
            int end = e[i];
            al.add(new Temp3(start, true));
            al.add(new Temp3(end, false));
        }

        Collections.sort(al, (t1, t2) -> {
            int indexCompare = Integer.compare(t1.year, t2.year);

            if (indexCompare != 0) {
                return indexCompare;
            }

            // true should come before false
            return Boolean.compare(t1.isStart, t2.isStart);
        });

        int max = 0;
        int curr = 0;
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).isStart == true) {
                curr = curr + 1;

            } else {
                curr = curr - 1;
            }
            max = Integer.max(curr, max);
        }

        return max;
    }
}

class Temp3 {

    int year;
    boolean isStart;

    Temp3(int y, boolean d) {
        year = y;
        isStart = d;
    }
}
