package com.array;

import java.util.ArrayList;

public class BuildingsWithOceanView {

    public static void main(String[] args) {
        int[] heights = {4, 2, 3, 2, 1};
        int[] res = new BuildingsWithOceanView().findBuildings(heights);
        for(int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public int[] findBuildings(int[] heights) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        int max = heights.length - 1;
        al.add(max);
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > heights[max]) {
                max = i;
                al.add(i);
            }
        }
        // System.out.println("res "+al);

        int[] res = new int[al.size()];
        int j = al.size() - 1;
        for (int i = 0; i < al.size(); i++) {
            res[j] = al.get(i);
            j--;
        }

        return res;
    }
}
