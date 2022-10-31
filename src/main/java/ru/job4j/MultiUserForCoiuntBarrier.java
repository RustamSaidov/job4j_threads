package ru.job4j;

public class MultiUserForCoiuntBarrier {
    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);

        Thread first = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");

                },
                "first"
        );
        Thread second = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "second"
        );
        first.start();
        second.start();

        for (int i = 0; i < countBarrier.getTotal(); i++) {
            try {
                Thread.sleep(1000);
                countBarrier.count();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
