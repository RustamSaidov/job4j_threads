package ru.job4j.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        memory.computeIfPresent(memory.get(model.getId()).getId(), (k, v) -> {
            if (model.getVersion() != v.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            return new Base(model.getId(), model.getVersion() + 1);
        });
        return true;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Map<Integer, Base> getMemory() {
        return new HashMap<>(memory);
    }
}