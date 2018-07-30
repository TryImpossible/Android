package com.barry.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class PhotographyActivity extends Activity {

    private String currentPhotoPath;
    private String currentMoviePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography);
    }

    public void click1(View v) {
        //啟動拍照的Activity
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(createImageFile()));

        startActivityForResult(intent, 10);
    }

    private File createImageFile() {
        String prefix = "JPEG_" + System.currentTimeMillis() + "_";
        String suffix = ".jpg";
        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(prefix, suffix, directory);
            currentPhotoPath = "file:" + image.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void click2(View v) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(createMovieFile()));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 20);
    }

    private File createMovieFile() {
        String prefix = "Movie_" + System.currentTimeMillis() + "_";
        String suffix = ".3gp";
        File directory = getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        File moive = null;
        try {
            moive = File.createTempFile(prefix, suffix, directory);
            currentMoviePath = "file:" + moive.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moive;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            Toast.makeText(this, "拍照完畢", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File file = new File(currentPhotoPath);
            Uri uri = Uri.fromFile(file);
            System.out.println("相片路徑：" + file.getAbsolutePath());
            intent.setData(uri);
            sendBroadcast(intent);

        } else if (requestCode == 20) {
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File file = new File(currentPhotoPath);
            Uri uri = Uri.fromFile(file);
            System.out.println("路徑：" + file.getAbsolutePath());
            intent.setData(uri);
            sendBroadcast(intent);
        }
    }
}
