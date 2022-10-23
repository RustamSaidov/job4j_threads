package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(10000);
            progress.interrupt();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        int i = 0;
        char[] process = {'|', '/', '\\'};
        try {
            Thread.sleep(500);
            while (!Thread.currentThread().isInterrupted()) {
                if (i == process.length) {
                    i = 0;
                }
                System.out.println("Loading ... " + process[i]);
                i++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


