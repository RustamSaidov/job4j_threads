package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {

    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        int mid = (from + to) / 2;
        // создаем задачи для сортировки частей
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
        // производим деление.
        // оно будет происходить, пока в частях не останется по одному элементу
        leftSort.fork();
        rightSort.fork();
        // объединяем полученные результаты
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }

//    public static int[] sort(int[] array) {
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
//    }

    public static void main(String[] args) {
        int[] array = new int[] {2,5,3,1,9,6,8,7,4,0};
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(array,0, array.length - 1);
        System.out.println(Arrays.toString(parallelMergeSort.compute()));

    }
}