    package com.graph;

    import java.util.Arrays;
    import java.util.PriorityQueue;

    public class CourseSchedule3 {

        public static void main(String[] args) {
            CourseSchedule3 cs = new CourseSchedule3();
            int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
            System.out.println(cs.scheduleCourse(courses));
        }

        public int scheduleCourse(int[][] courses) {
            Arrays.sort(courses, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);
            int start = 0;
            for (int i = 0; i < courses.length; i++) {
                if (start + courses[i][0] <= courses[i][1]) {
                    pq.add(courses[i]);
                    start = start + courses[i][0];
                } else {
                    int[] peek = pq.peek();
                    if (!pq.isEmpty() && peek[0] > courses[i][0]) {
                        pq.poll();
                        pq.add(courses[i]);
                        start = start - peek[0];
                        start = start + courses[i][0];
                    }
                }
            }
            return pq.size();
        }
    }
