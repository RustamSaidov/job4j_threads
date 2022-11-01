package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    final int steps = 14;
    final int queueMaxLength = 5;
    int totalCount = 0;
    int count = 0;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        while (queue.size() >= queueMaxLength || totalCount == steps) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        System.out.println("value " + value + " is added");
        count++;
        totalCount++;
        notifyAll();
    }

    public synchronized T poll() {
        while (queue.size() == 0) {
            try {
                count = 0;
                notifyAll();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T r = queue.poll();
        System.out.println("value " + r + " is got");
        return r;

    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>();
        Thread first = new Thread(
                () -> {
                    while (sbq.totalCount < sbq.steps) {
                        sbq.offer(sbq.totalCount);
                    }
                    System.out.println("first ended");
                },
                "first"

        );
        Thread second = new Thread(
                () -> {
                    while (sbq.totalCount < sbq.steps || !sbq.queue.isEmpty()) {
                        sbq.poll();
                    }
                    System.out.println("second ended");
                },
                "second"
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
