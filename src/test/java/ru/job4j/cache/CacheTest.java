package ru.job4j.cache;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void methodsTest() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 0);
        Base model2 = new Base(2, 0);
        Base model3 = new Base(2, 0);
        Base model4 = new Base(2, 5);
        cache.add(model1);
        cache.add(model2);
        assertEquals(model1, cache.getMemory().get(model1.getId()));
        assertEquals(model2, cache.getMemory().get(model2.getId()));
        cache.delete(model1);
        assertNull(cache.getMemory().get(model1.getId()));
        cache.update(model3);
        assertEquals(model3.getId(), cache.getMemory().get(2).getId());
        assertEquals(model3.getName(), cache.getMemory().get(2).getName());
        assertEquals(model3.getVersion() + 1, cache.getMemory().get(2).getVersion());
        Assertions.assertThrows(OptimisticException.class, () -> {
            cache.update(model2);
        });
        Assertions.assertThrows(OptimisticException.class, () -> {
            cache.update(model4);
        });
    }
}