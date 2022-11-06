package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer future;
        Integer now;
        do {
            now = count.get();
            future = now++;
            if (count == null) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
        } while (!count.compareAndSet(now, future));
    }

    public int get() {
        Integer now;
        do {
            now = count.get();
            if (count == null) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
        } while (!count.compareAndSet(now, now));
        return now;
    }
}