package com.searching;

public class SearchIn2DMatrix {

    public static void main(String[] args) {
        SearchIn2DMatrix obj = new SearchIn2DMatrix();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        System.out.println(obj.searchMatrix(matrix, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int h = row * col - 1;
        int l = 0;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int r = m / col;
            int c = m % col;
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }

        return false;
    }
}
