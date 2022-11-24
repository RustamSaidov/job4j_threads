package ru.job4j.pools;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParallelObjectSearchTest {

    @Test
    void whenElementIsInTheBigArray() {
        ObjForSearch obj1 = new ObjForSearch("Tom");
        ObjForSearch[] array = new ObjForSearch[]{
                new ObjForSearch("Bill"),
                new ObjForSearch("Tim"),
                new ObjForSearch("Dough"),
                new ObjForSearch("Sam"),
                obj1,
                new ObjForSearch("George"),
                new ObjForSearch("Will"),
                new ObjForSearch("Stanley"),
                new ObjForSearch("Grag"),
                new ObjForSearch("Ivan"),
                new ObjForSearch("Igor"),
                new ObjForSearch("Oleg"),
                new ObjForSearch("Phoma")
        };
        ParallelObjectSearch parallelObjectSearch = ParallelObjectSearch.getArrayAndObjForSearch(array, obj1);
        assertEquals(parallelObjectSearch.invoke(),4);
    }

    @Test
    void whenElementIsInTheShortArray() {
        ObjForSearch obj1 = new ObjForSearch("Tom");
        ObjForSearch[] array = new ObjForSearch[]{
                new ObjForSearch("Bill"),
                new ObjForSearch("Tim"),
                obj1,
                new ObjForSearch("George"),
                new ObjForSearch("Will")
        };
        ParallelObjectSearch parallelObjectSearch = ParallelObjectSearch.getArrayAndObjForSearch(array, obj1);
        assertEquals(parallelObjectSearch.invoke(),2);
    }

    @Test
    void whenElementIsNotFound() {
        ObjForSearch obj1 = new ObjForSearch("Tom");
        ObjForSearch[] array = new ObjForSearch[]{
                new ObjForSearch("Bill"),
                new ObjForSearch("Tim"),
                new ObjForSearch("Dough"),
                new ObjForSearch("Sam"),
                new ObjForSearch("George"),
                new ObjForSearch("Will"),
                new ObjForSearch("Stanley"),
                new ObjForSearch("Grag"),
                new ObjForSearch("Ivan"),
                new ObjForSearch("Igor"),
                new ObjForSearch("Oleg"),
                new ObjForSearch("Phoma")
        };
        ParallelObjectSearch parallelObjectSearch = ParallelObjectSearch.getArrayAndObjForSearch(array, obj1);
        assertEquals(parallelObjectSearch.invoke(),-1);
    }

    @Test
    void whenElementIsAnotherType() {
        Object obj1 = new Object();
        ObjForSearch[] array = new ObjForSearch[]{
                new ObjForSearch("Bill"),
                new ObjForSearch("Tim"),
                new ObjForSearch("Dough"),
                new ObjForSearch("Sam"),
                new ObjForSearch("George"),
                new ObjForSearch("Will"),
                new ObjForSearch("Stanley"),
                new ObjForSearch("Grag"),
                new ObjForSearch("Ivan"),
                new ObjForSearch("Igor"),
                new ObjForSearch("Oleg"),
                new ObjForSearch("Phoma")
        };
        ParallelObjectSearch parallelObjectSearch = ParallelObjectSearch.getArrayAndObjForSearch(array, obj1);
        Assert.
        assertEquals(parallelObjectSearch.invoke(),-1);
    }
}


