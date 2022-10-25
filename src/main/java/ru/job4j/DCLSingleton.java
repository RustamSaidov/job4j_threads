package ru.job4j;

public final class DCLSingleton {

    /*Ошибка была в том, что поле inst, которое мы проверяем на null не было volatile, а значит могла возникнуть ситуация,
    когда два разных треда могли одновременно попытаться создать экземпляр синглтона, и inst был бы не заблокирован,
    что привело бы к созданию двух экземпляров синглтона.
     */

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }
}