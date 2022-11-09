package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class CASCountTest {
    public static final int LIMIT = 100;

    @Test
    void incrementTest() throws InterruptedException {
        CASCount casCount = new CASCount(new AtomicReference<>(0));
        Thread first = new Thread(
                () -> {
                    while (casCount.get() < LIMIT) {
                        casCount.increment();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (casCount.get() < LIMIT) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(LIMIT, casCount.get());
    }
}