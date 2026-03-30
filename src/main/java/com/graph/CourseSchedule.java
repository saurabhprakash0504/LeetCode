package com.graph;

import java.util.ArrayList;

public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};

        CourseSchedule obj = new CourseSchedule();
        boolean res = obj.canFinish(numCourses, prerequisites);
        System.out.println(res);

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i < numCourses;i++){
            adj.add(i, new ArrayList<Integer>());
        }
        createAdjList(numCourses, adj, prerequisites);

        boolean[] vis = new boolean[numCourses];
        boolean[] dfsChain = new boolean[numCourses];

        for(int i=0;i<numCourses; i++){
            if(!vis[i]){
                if(isCyclePresent(adj, i, -1, numCourses, vis, dfsChain)){
                    return false;
                }
            }
        }


        return true;


    }


    boolean isCyclePresent(ArrayList<ArrayList<Integer>> adj, int curr, int par, int numCourses, boolean[] vis , boolean[] dfsChain ){

        vis[curr]= true;
        dfsChain[curr] = true;

        ArrayList<Integer> neigh = adj.get(curr);

        for(Integer c : neigh){

            if(!vis[c]){
                if(isCyclePresent(adj, c, curr, numCourses, vis, dfsChain)){
                    return true;
                }
            }else if(vis[c] && dfsChain[c]){
                return true;
            }


        }
        dfsChain[curr] = false;
        return false;

    }


    void createAdjList(int numCourses, ArrayList<ArrayList<Integer>> adj, int[][] prerequisites){

        for(int i =0;i< prerequisites.length;i++){

            int p = prerequisites[i][0];
            int d = prerequisites[i][1];

            adj.get(d).add(p);

        }

    }


}
