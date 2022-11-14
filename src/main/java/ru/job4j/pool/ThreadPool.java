package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size * 10);

    public ThreadPool() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread();
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            while (!Thread.currentThread().isInterrupted()) {
                while (!tasks.getQueue().isEmpty()) {
                    tasks.getQueue().poll().run();
                }
            }
        });
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(t -> t.interrupt());
    }

    public static void main(String[] args) throws InterruptedException {
        final int[] i = {0};
        ThreadPool threadPool = new ThreadPool();
        Runnable job1 = () -> {
            System.out.println(i[0]);
            i[0] = i[0] + 1;
            Thread.currentThread().interrupt();
        };
        Runnable job2 = () -> {
            System.out.println(i[0]);
            i[0] = i[0] + 1;
            Thread.currentThread().interrupt();
        };
        Runnable job3 = () -> {
            System.out.println(i[0]);
            i[0] = i[0] + 1;
            Thread.currentThread().interrupt();
        };
        Runnable job4 = () -> {
            System.out.println(i[0]);
            i[0] = i[0] + 1;
            Thread.currentThread().interrupt();
        };
        Runnable job5 = () -> {
            System.out.println(i[0]);
            i[0] = i[0] + 1;
            Thread.currentThread().interrupt();
        };
        Runnable job6 = () -> {
            System.out.println(i[0]);
            i[0] = i[0] + 1;
            Thread.currentThread().interrupt();
        };
        threadPool.work(job1);
        threadPool.work(job2);
        threadPool.work(job3);
        threadPool.work(job4);
        threadPool.work(job5);
        threadPool.work(job6);
        threadPool.shutdown();
        threadPool.threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
