package com.barry.jni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TransmitActivity extends Activity {

    int[] array = {1, 2, 3, 4, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmit);
    }

    public void click(View v) {
        JNIUtil.transmit(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
