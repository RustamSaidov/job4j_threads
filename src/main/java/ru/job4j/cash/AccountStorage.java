package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();
    private final int inc = 0;

    public synchronized boolean add(Account account) {
        boolean result = false;
        if (!accounts.containsKey(account.id())) {
            result = true;
            accounts.put(account.id(), account);
        }
        return result;
    }

    public synchronized boolean update(Account account) {
        boolean result = false;
        if (accounts.containsKey(account.id())) {
            accounts.replace(account.id(), account);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(int id) {
        boolean result = false;
        if (accounts.containsKey(id)) {
            accounts.remove(id);
            result = true;
        }
        return result;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));


    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (accounts.containsKey(fromId) && accounts.containsKey(toId)) {
            int fromAmount = accounts.get(fromId).amount();
            int toAmount = accounts.get(toId).amount();
            if (fromAmount >= amount) {
                accounts.replace(fromId, new Account(fromId, fromAmount - amount));
                accounts.replace(toId, new Account(toId, toAmount + amount));
                result = true;
            }
        }
        return result;
    }
}
