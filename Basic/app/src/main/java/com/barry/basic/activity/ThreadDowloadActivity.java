package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.barry.basic.R;

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
import java.net.ProtocolException;
import java.net.URL;

public class ThreadDowloadActivity extends Activity {

    String path = "http://192.168.16.42:8080/android-basic/shadowsocks.apk";
    int threadCount = 3;
    int finishedThread = 0;
    int downloadProgress = 0;

    private ProgressBar pb;
    private TextView tv;

    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            tv.setText((long)pb.getProgress() * 100 / pb.getMax() + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_dowload);

        pb = (ProgressBar) findViewById(R.id.pb);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void click(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                download();
            }
        }).start();
    }

    public void download() {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                int length = conn.getContentLength();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + getNameFromPath(path));
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.setLength(length);
                raf.close();

                pb.setMax(length);

                int size = length / threadCount;
                for (int i = 0; i < threadCount; i++) {
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    if (i == threadCount - 1) {
                        endIndex = length;
                    }
                    System.out.println("线程" + i + "下载的区间：" + startIndex + " ~ " + endIndex);
                    new ThreadDownload(i, startIndex, endIndex).start();
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameFromPath(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    class ThreadDownload extends Thread {

        int threadId;
        int startIndex;
        int endIndex;

        public ThreadDownload(int threadId, int startIndx, int endIndex) {
            super();
            this.threadId = threadId;
            this.startIndex = startIndx;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            try {
                File fileProgress = new File(Environment.getExternalStorageDirectory(), "/" + threadId + ".txt");
                int lastProgress = 0;
                if (fileProgress.exists()) {
                    FileInputStream fis = new FileInputStream(fileProgress);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    lastProgress = Integer.parseInt(br.readLine());
                    startIndex += lastProgress;
                    fis.close();

                    downloadProgress += lastProgress;
                    handler.sendEmptyMessage(1);
                }
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                if (conn.getResponseCode() == 206) {
                    InputStream is = conn.getInputStream();
                    int len;
                    byte[] b = new byte[1024];
                    int total = lastProgress;
                    File file = new File(Environment.getExternalStorageDirectory() + "/" + getNameFromPath(path));
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    raf.seek(startIndex);
                    while ((len = is.read(b)) != -1) {
                        raf.write(b, 0, len);
                        total += len;
                        System.out.println("線程" + threadId + "下載了：" + total);

                        RandomAccessFile rafProgress = new RandomAccessFile(fileProgress, "rwd");
                        rafProgress.write((total + "").getBytes());
                        rafProgress.close();

                        downloadProgress += len;
                        pb.setProgress(downloadProgress);

                        handler.sendEmptyMessage(1);
                    }
                    raf.close();
                    System.out.println("線程" + threadId + "下載完畢------------");

                    finishedThread++;
                    synchronized (path) {
                        if (finishedThread == threadCount) {
                            for (int i = 0; i < threadCount; i++) {
                                File f = new File(Environment.getExternalStorageDirectory(), "/" + i + ".txt");
                                f.delete();
                            }
                            finishedThread = 0;
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
