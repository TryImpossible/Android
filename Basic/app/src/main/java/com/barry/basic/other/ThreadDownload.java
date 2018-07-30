package com.barry.basic.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bynn on 2018/1/22.
 */
public class ThreadDownload {

    static String path = "http://192.168.16.42:8080/android-basic/shadowsocks.apk";
    static int threadCount = 3;
    static int finishedThread = 0;

    public static void main(String[] args) {
        System.out.println("運行了");

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestMethod("GET");
            if (200 == conn.getResponseCode()) {
                int length = conn.getContentLength();
                File file = new File(getNameFromPath(path));
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.setLength(length);
                raf.close();

                System.out.println("總長度：" + length);
                int size = length / threadCount;
                for (int i = 0; i < threadCount; i++) {
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    if (i == threadCount - 1) {
                        endIndex = length;
                    }
                    System.out.println("線程" + i + "下載區間:" + startIndex + " ~ " + endIndex);
                    new DownloadThread(i, startIndex, endIndex).start();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNameFromPath(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

}

class DownloadThread extends Thread {

    int threadId;
    int startIndex;
    int endIndex;

    DownloadThread(int threadId, int startIndex, int endIndex) {
        super();
        this.threadId = threadId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        try {
            File fileProgress = new File(threadId + ".txt");
            int lastProgress = 0;
            if (fileProgress.exists()) {
                FileInputStream fis = new FileInputStream(fileProgress);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                lastProgress = Integer.parseInt(br.readLine());
                startIndex += lastProgress;
                fis.close();
            }
            URL url = new URL(ThreadDownload.path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
            if (conn.getResponseCode() == 206) {
                InputStream is = conn.getInputStream();


                byte[] b = new byte[1024];
                int len = 0;
                int total = lastProgress;
                File file = new File(ThreadDownload.getNameFromPath(ThreadDownload.path));
                RandomAccessFile raf  = new RandomAccessFile(file, "rwd");
                raf.seek(startIndex);
                while ((len = is.read(b)) != -1) {
                    raf.write(b, 0, len);
                    total += len;
                    System.out.println("線程" + threadId + "下載了：" + total);

                    RandomAccessFile rafProgress =  new RandomAccessFile(fileProgress, "rwd");
                    rafProgress.write((total + "").getBytes());
                    rafProgress.close();
                }
                raf.close();
                System.out.println("線程" + threadId + "下載完畢----------------------");
            }

            ThreadDownload.finishedThread++;
            synchronized (ThreadDownload.path) {
                if (ThreadDownload.finishedThread == ThreadDownload.threadCount) {
                    for (int i = 0; i < ThreadDownload.threadCount; i++) {
                        File f = new File(i + ".txt");
                        f.delete();
                    }
                    ThreadDownload.finishedThread = 0;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
