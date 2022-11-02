package ru.job4j.buffer;

import ru.job4j.SimpleBlockingQueue;
public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final int maxIndex = 3;
        final int[] count = {0};
        final Thread consumer = new Thread(
                () -> {
                    while (count[0] < maxIndex && queue.getQueue().isEmpty()) {
                        try {
                            System.out.println(queue.poll());
                            count[0]++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    Thread.currentThread().interrupt();
                }
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index < maxIndex; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
    }
}