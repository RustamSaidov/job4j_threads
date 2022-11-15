package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger j = new AtomicInteger();
        ThreadPool threadPool = new ThreadPool();
        Runnable job1 = () -> {
            System.out.println("This is thread with the name " + Thread.currentThread().getName() + " and it outputs value " + j.getAndIncrement());
        };
        Runnable job2 = () -> {
            System.out.println("This is thread with the name " + Thread.currentThread().getName() + " and it outputs value " + j.getAndIncrement());
        };
        Runnable job3 = () -> {
            System.out.println("This is thread with the name " + Thread.currentThread().getName() + " and it outputs value " + j.getAndIncrement());
        };
        Runnable job4 = () -> {
            System.out.println("This is thread with the name " + Thread.currentThread().getName() + " and it outputs value " + j.getAndIncrement());
        };
        Runnable job5 = () -> {
            System.out.println("This is thread with the name " + Thread.currentThread().getName() + " and it outputs value " + j.getAndIncrement());
        };
        Runnable job6 = () -> {
            System.out.println("This is thread with the name " + Thread.currentThread().getName() + " and it outputs value " + j.getAndIncrement());
        };
        threadPool.work(job1);
        threadPool.work(job2);
        threadPool.work(job3);
        threadPool.work(job4);
        threadPool.work(job5);
        threadPool.work(job6);
        threadPool.shutdown();
    }
}