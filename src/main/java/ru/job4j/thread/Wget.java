package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String urlOfInputFile;
    private final String addressOfOutputFile;
    private final int speed;
    public static final int ONE_SECOND_IN_MILLISEC = 1000;

    public Wget(String urlOfInputFile, String urlOfOutputFile, int speed) {
        this.urlOfInputFile = urlOfInputFile;
        this.addressOfOutputFile = urlOfOutputFile;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlOfInputFile).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(addressOfOutputFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData = downloadData + bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finish = System.currentTimeMillis();
                if (downloadData >= speed) {
                    downloadData = 0;
                    if (finish - start < ONE_SECOND_IN_MILLISEC) {
                        long delayTime = ONE_SECOND_IN_MILLISEC - (finish - start);
                        Thread.sleep(delayTime);
                    }
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Входящие данные:
        "-urlOfInputFile=https://proof.ovh.net/files/10Mb.dat" -speed=512000 -addressOfOutputFile=10Mb_tmp.dat
        Запланированное время работы =20с
        */

        ArgsName.checkArrayOnEmptiness(args);
        long start = System.currentTimeMillis();
        ArgsName jvm = ArgsName.of(new String[]{args[0], args[1], args[2]});
        Thread wget = new Thread(new Wget(jvm.get("urlOfInputFile"), jvm.get("addressOfOutputFile"), Integer.parseInt(jvm.get("speed"))));
        wget.start();
        wget.join();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}