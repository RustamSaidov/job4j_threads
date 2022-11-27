package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] resultArray = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            resultArray[i] = new Sums(0, 0);
            for (int j = 0; j < matrix[0].length; j++) {
                resultArray[i].setRowSum(resultArray[i].getRowSum() + matrix[i][j]);
                resultArray[i].setColSum(resultArray[i].getColSum() + matrix[j][i]);
            }
        }
        return resultArray;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] resultArray = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            resultArray[i] = calcRowSumAndColSum(new Sums(0, 0), matrix, i).get();
        }
        return resultArray;
    }

    public static CompletableFuture<Sums> calcRowSumAndColSum(Sums sum, int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(
                () -> {
                    for (int j = 0; j < matrix[0].length; j++) {
                        sum.setRowSum(sum.getRowSum() + matrix[i][j]);
                        sum.setColSum(sum.getColSum() + matrix[j][i]);
                    }
                    return sum;
                }
        );
    }
}
