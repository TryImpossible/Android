package com.barry.basic.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.barry.basic.R;

import java.io.File;

public class RemainingStorageActivity extends Activity {
    public final static String TAG = "RemainingStorage";

    private TextView tv_waring;
    private TextView tv_remainingStorage;
    private TextView tv_totalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining_storage);
        tv_waring = (TextView) findViewById(R.id.tv_waring);
        tv_totalStorage = (TextView) findViewById(R.id.tv_totalStorage);
        tv_remainingStorage = (TextView) findViewById(R.id.tv_remainingStorage);
    }

    public void click(View v) {
        Log.i(TAG, "點擊了");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize;
            long totalBlocks;
            long availableBlocks;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = stat.getBlockSizeLong();
                totalBlocks = stat.getBlockCountLong();
                availableBlocks = stat.getAvailableBlocksLong();
            } else {
                blockSize = stat.getBlockSize();
                totalBlocks = stat.getBlockCount();
                availableBlocks = stat.getAvailableBlocks();
            }
            tv_totalStorage.setText("SD卡總容量：" + formatSize(totalBlocks * blockSize));
            tv_remainingStorage.setText("SD卡剩餘容量：" + formatSize(availableBlocks * blockSize));
        } else {
            tv_waring.setText("沒在外部存儲");
            tv_waring.setTextColor(Color.RED);
        }
    }

    private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }
}
