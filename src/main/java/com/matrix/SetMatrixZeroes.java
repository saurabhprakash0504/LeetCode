package com.matrix;

import java.util.ArrayList;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        SetMatrixZeroes obj = new SetMatrixZeroes();
        obj.setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        ArrayList<int[]> al = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 0) {
                    al.add(new int[]{r, c});
                }
            }
        }

        for (int[] a : al) {

            int r = a[0];
            int c = a[1];

            for (int ii = 0; ii < row; ii++) {
                matrix[ii][c] = 0;
            }

            for (int jj = 0; jj < col; jj++) {
                matrix[r][jj] = 0;
            }
        }
    }
}
