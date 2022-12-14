package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelObjectSearch<E> extends RecursiveTask<Integer> {
    private final E[] array;
    private final int from;
    private final int to;
    private final E obj;

    private ParallelObjectSearch(E[] array, int from, int to, E obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = from; i < to; i++) {
                if (obj.equals(array[i])) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;

        ParallelObjectSearch leftSort = new ParallelObjectSearch(array, from, mid, obj);
        ParallelObjectSearch rightSort = new ParallelObjectSearch(array, mid + 1, to, obj);

        leftSort.fork();
        rightSort.fork();
        Integer left = (Integer) leftSort.join();
        Integer right = (Integer) rightSort.join();
        return Math.max(left, right);
    }

    public static Integer getArrayAndObjForSearch(Object[] array, Object obj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (Integer) forkJoinPool.invoke(new ParallelObjectSearch(array, 0, array.length - 1, obj));
    }
}