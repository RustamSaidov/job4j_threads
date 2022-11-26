package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParallelObjectSearchTest {

    @Test
    void whenElementIsIntegerInTheShortArray() {
        Integer integer = 5;
        Integer[] array = new Integer[]{
                2,
                11,
                integer,
                22,
                444
        };
        assertEquals(ParallelObjectSearch.getArrayAndObjForSearch(array, integer), 2);
    }

    @Test
    void whenElementIsStringInTheShortArray() {
        ObjForSearch obj1 = new ObjForSearch("Tom");
        ObjForSearch[] array = new ObjForSearch[]{
                new ObjForSearch("Bill"),
                new ObjForSearch("Tim"),
                obj1,
                new ObjForSearch("George"),
                new ObjForSearch("Will")
        };
        assertEquals(ParallelObjectSearch.getArrayAndObjForSearch(array, obj1), 2);
    }

    @Test
    void whenElementIsStringInTheBigArray() {
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
        assertEquals(ParallelObjectSearch.getArrayAndObjForSearch(array, obj1), 4);
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
        assertEquals(ParallelObjectSearch.getArrayAndObjForSearch(array, obj1), -1);
    }
}


