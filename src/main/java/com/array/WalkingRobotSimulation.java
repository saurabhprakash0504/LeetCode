package com.array;

import java.util.HashSet;
import java.util.Set;

public class WalkingRobotSimulation {

    public static void main(String[] args) {

        WalkingRobotSimulation solution = new WalkingRobotSimulation();
        int[] commands = {4, -1, 3};
        int[][] obstacles = {};
        int result = solution.robotSim(commands, obstacles);
        System.out.println(result); // Output: 25
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: N, E, S, W
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0; // start facing north

        // Store obstacles in a hashset for O(1) lookup
        Set<String> obs = new HashSet<>();
        for (int[] o : obstacles) {
            obs.add(o[0] + "," + o[1]);
        }

        int x = 0, y = 0;
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                dir = (dir + 1) % 4; // turn right
            } else if (cmd == -2) {
                dir = ((dir - 1) + 4) % 4; // turn left
            } else {
                // move step by step
                for (int step = 0; step < cmd; step++) {

                    x = x + dirs[dir][0];
                    y = y + dirs[dir][1];

                    if (obs.contains(x + "," + y)) {
                        x = x - dirs[dir][0];
                        y = y - dirs[dir][1];
                        break;
                    }

                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
}
