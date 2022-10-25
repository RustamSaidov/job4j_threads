package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String urlOfInputFile;
    private final String addressOfOutputFile;
    private final int speed;

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
            long start = System.nanoTime();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                if (downloadData == 0) {
                    start = System.nanoTime();
                }
                downloadData=downloadData+bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finish = System.nanoTime();
                if (downloadData >= speed && finish - start < 1000000000) {
                        long diff = 1000000000 - (finish - start);
                        long delayTime = diff / 1000000;
                        Thread.sleep(delayTime);
                        downloadData = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Входящие данные:
        -urlOfInputFile=https://proof.ovh.net/files/10Mb.dat -speed=512000 -addressOfOutputFile=10Mb_tmp.dat
        Запланированное время работы =20с
        */
        long start = System.nanoTime();
        ArgsName jvm = ArgsName.of(new String[]{args[0], args[1], args[2]});
        Thread wget = new Thread(new Wget(jvm.get("urlOfInputFile"), jvm.get("addressOfOutputFile"), Integer.parseInt(jvm.get("speed"))));
        wget.start();
        wget.join();
        long finish = System.nanoTime();
        System.out.println(finish-start);
    }
}