package ru.job4j.threads.threadlocal;

public class ThreadLocalDemo {
    static ThreadLocal<String> tl = new ThreadLocal<>(); /* объявляем статическую переменную ThreadLocal */

    public static void main(String[] args) throws InterruptedException {
        Thread first = new FirstThread();
        Thread second = new SecondThread();
        tl.set("Это поток main."); /* записываем данные в переменную ThreadLocal */
        System.out.println(tl.get()); /* получаем данные из этой переменной и выводим на печать */
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
