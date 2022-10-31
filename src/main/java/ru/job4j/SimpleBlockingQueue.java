package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    final int steps = 10;
    final int queueMaxLength = 5;
    int totalcount=0;
    int count = 0;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        while (queue.size()>=1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
    }

    public synchronized T poll() {
        while (queue.size() == 0) {
            try {
                count=0;
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue sbq = new SimpleBlockingQueue<Integer>();
        Thread first = new Thread(
                () -> {
                    while (sbq.totalcount < sbq.steps) {
                        sbq.offer(sbq.totalcount);
                        System.out.println(Thread.currentThread().getName() + " added " + sbq.totalcount + " value");
                        sbq.count++;
                        sbq.totalcount++;
                    }
                },
                "first"
        );
        Thread second = new Thread(
                () -> {
                    while (sbq.totalcount < sbq.steps) {
                        System.out.println(Thread.currentThread().getName() + " get " + sbq.poll() + " value");
                    }
                },
                "second"
        );
        first.start();
        second.start();
        first.join();
        second.join();

    }
}
