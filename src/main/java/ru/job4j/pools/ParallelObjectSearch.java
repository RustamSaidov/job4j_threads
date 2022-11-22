/*package ru.job4j.pools;

import java.util.OptionalInt;
import java.util.concurrent.RecursiveTask;

public class ParallelObjectSearch extends RecursiveTask<OptionalInt> {
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
    protected OptionalInt compute() {
        OptionalInt result = OptionalInt.empty();
        if (to-from<=10) {
            for (int i = from; i < to; i++) {
                if(obj.equals(array[i])){
                    return OptionalInt.of(i);
                }
            }
        }
        int mid = (from + to) / 2;

        ParallelObjectSearch leftSort = new ParallelObjectSearch(array, from, mid, obj);
        ParallelObjectSearch rightSort = new ParallelObjectSearch(array, mid + 1, to, obj);

        leftSort.fork();
        rightSort.fork();
        OptionalInt left = leftSort.join();
        OptionalInt right = rightSort.join();
        if(left.isPresent()){
           result = left;
        }else {
            result = right;
        }
        return result;
    }


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
        System.out.println(parallelObjectSearch.invoke().getAsInt());
    }
}


 */