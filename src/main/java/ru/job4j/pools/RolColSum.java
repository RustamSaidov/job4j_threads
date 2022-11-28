package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] resultArray = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            resultArray[i] = new Sums(rowSum, colSum);

        }
        return resultArray;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] resultArray = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            resultArray[i] = calcRowSumAndColSum(matrix, i).get();
        }
        return resultArray;
    }

    public static CompletableFuture<Sums> calcRowSumAndColSum(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int rowSum = 0;
                    int colSum = 0;
                    for (int j = 0; j < matrix[0].length; j++) {
                        rowSum += matrix[i][j];
                        colSum += matrix[j][i];
                    }
                    return new Sums(rowSum, colSum);
                }
        );
    }
}
