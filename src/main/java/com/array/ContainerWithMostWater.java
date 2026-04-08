package com.array;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = solution.maxArea(height);
        System.out.println(result); // Output: 49
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxArea = 0;

        while (l < r) {
            int leftH = height[l];
            int righttH = height[r];
            int minH = Integer.min(leftH, righttH);
            int wid = r - l;
            int currArea = minH * wid;
            maxArea = Integer.max(currArea, maxArea);
            if (leftH < righttH) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }
}
