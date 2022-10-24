package ru.job4j.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + "is working");
            System.out.println(second.getName() + "is working");
        }
        System.out.println("Work of additional threads has ends.");
    }
}