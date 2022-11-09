package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(AtomicReference<Integer> count) {
        this.count = count;
    }

    public void increment() {
        int future;
        int now;
        do {
            now = get();
            future = now + 1;
        } while (!count.compareAndSet(get(), future));
    }

    public int get() {
        return count.get();
    }
}
