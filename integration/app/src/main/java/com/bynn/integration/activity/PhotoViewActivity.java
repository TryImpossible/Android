package com.bynn.integration.activity;

import android.os.Bundle;

import com.bynn.bynnframework.widget.photoview.PhotoView;
import com.bynn.integration.R;

public class PhotoViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_photo_view);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.pic1);
//        photoView.setImageURI(Uri.parse("http://pic36.photophoto.cn/20150706/0022005461599587_b.jpg"));
    }
}
