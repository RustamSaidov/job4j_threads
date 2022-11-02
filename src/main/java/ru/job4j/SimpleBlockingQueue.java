package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int limit;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public Queue<T> getQueue() {
        return queue;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;

    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(10);
        Thread first = new Thread(() -> {
            while (sbq.queue.size() < sbq.limit) {
                try {
                    sbq.offer(sbq.queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "first"

        );
        Thread second = new Thread(() -> {
            while (sbq.queue.size() < sbq.limit) {
                try {
                    sbq.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "second");
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
