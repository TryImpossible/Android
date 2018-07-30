package com.bynn.integration;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bynn.bynnframework.widget.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
//        tv.setText("buildType:" + Constants.ENVIRONMENT + "\nAPI:" + Constants.API_URL);

        tv.setText("第一張圖片");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoView();
            }
        });

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
//        photoView.setImageResource(R.drawable.pic1);
        photoView.setImageURI(Uri.parse("http://pic36.photophoto.cn/20150706/0022005461599587_b.jpg"));
    }

    private void openPhotoView() {

    }
}
