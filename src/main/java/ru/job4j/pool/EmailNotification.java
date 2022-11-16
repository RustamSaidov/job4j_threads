package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String body = """
                        subject = Notification %s to email %s.
                        body = Add a new event to %s""".formatted(user.getUsername(), user.getEmail(), user.getUsername());
                send(user.getUsername(), body, user.getEmail());

            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
