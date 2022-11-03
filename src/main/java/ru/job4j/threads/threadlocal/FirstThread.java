package ru.job4j.threads.threadlocal;

public class FirstThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.tl.set("Это поток 1."); // записываем данные в переменную ThreadLocal первой нити
        System.out.println(ThreadLocalDemo.tl.get()); // получаем данные из этой переменной и выводим на печать
    }
}