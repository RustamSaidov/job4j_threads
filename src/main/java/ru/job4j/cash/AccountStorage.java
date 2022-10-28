package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.put(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        Optional<Account> fromAcc = Optional.ofNullable(accounts.get(fromId));
        Optional<Account> toAcc = Optional.ofNullable(accounts.get(toId));
        if (fromAcc.isPresent() && toAcc.isPresent()) {
            int fromAmount = fromAcc.get().amount();
            int toAmount = toAcc.get().amount();
            if (fromAmount >= amount) {
                accounts.replace(fromId, new Account(fromId, fromAmount - amount));
                accounts.replace(toId, new Account(toId, toAmount + amount));
                result = true;
            }
        }
        return result;
    }
}
