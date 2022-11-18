package ru.job4j.pools;

import java.sql.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelObjectSearch extends RecursiveTask<ObjForSearch[]> {
    private final ObjForSearch[] array;
    private final int from;
    private final int to;
    private final ObjForSearch obj;

    public ParallelObjectSearch(ObjForSearch[] array, int from, int to, ObjForSearch obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    @Override
    protected Optional compute() {
        if (array.length<=10) {
            for (int i = 0; i < array.length; i++) {
                if(obj.equals(array[i])){
                    return i;
                }
            }
        }
        int mid = (from + to) / 2;
        // создаем задачи для сортировки частей
        ParallelObjectSearch leftSort = new ParallelObjectSearch(array, from, mid, obj);
        ParallelObjectSearch rightSort = new ParallelObjectSearch(array, mid + 1, to, obj);
        // производим деление.
        // оно будет происходить, пока в частях не останется по одному элементу
        leftSort.fork();
        rightSort.fork();
        // объединяем полученные результаты
        ObjForSearch[] left = leftSort.join();
        ObjForSearch[] right = rightSort.join();
        return MergeSort.merge(left, right);
//        if (from == to) {
//            return new ObjForSearch[] {array[from]};
//        }
//        int mid = (from + to) / 2;
//        // создаем задачи для сортировки частей
//        ParallelObjectSearch leftSort = new ParallelObjectSearch(array, from, mid, obj);
//        ParallelObjectSearch rightSort = new ParallelObjectSearch(array, mid + 1, to, obj);
//        // производим деление.
//        // оно будет происходить, пока в частях не останется по одному элементу
//        leftSort.fork();
//        rightSort.fork();
//        // объединяем полученные результаты
//        ObjForSearch[] left = leftSort.join();
//        ObjForSearch[] right = rightSort.join();
//        return MergeSort.merge(left, right);
    }

//    public static int[] sort(int[] array) {
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        return forkJoinPool.invoke(new ParallelObjectSearch(array, 0, array.length - 1));
//    }

    public static void main(String[] args) {
        ObjForSearch obj1 = new ObjForSearch("Tom");
        ObjForSearch[] array = new ObjForSearch[]{
                new ObjForSearch("Bill"),
                new ObjForSearch("Tim"),
                new ObjForSearch("Dough"),
                new ObjForSearch("Sam"),
                new ObjForSearch("George"),
                obj1,
                new ObjForSearch("Will"),
                new ObjForSearch("Stanley"),
                new ObjForSearch("Grag"),
                new ObjForSearch("Ivan"),
                new ObjForSearch("Igor"),
                new ObjForSearch("Oleg"),
                new ObjForSearch("Phoma")
        };
        ParallelObjectSearch parallelObjectSearch = new ParallelObjectSearch(array,0, array.length - 1, obj1);
        System.out.println(Arrays.toString(parallelObjectSearch.compute()));

    }
}