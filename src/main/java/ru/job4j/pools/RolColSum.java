/*
package ru.job4j.pools;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] resultArray = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            Sums sums = resultArray[i];
            for (int j = 0; j < matrix[0].length; j++) {
                sums.setRowSum(sums.getRowSum()+matrix[i][j]);
            }
        }
        for (int j = 0; j < matrix.length; j++) {
            Sums sums = resultArray[j];
            for (int i = 0; i < matrix[0].length; i++) {
                sums.setRowSum(sums.getRowSum()+matrix[j][i]);
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]

    }

}

 */
