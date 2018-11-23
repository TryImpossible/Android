package com.barry.mvvm.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast;

    /**
     * show Toast
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * show Toast
     * @param context
     * @param msgId
     */
    public static void show(Context context, int msgId) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msgId);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
