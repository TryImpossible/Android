package com.barry.jni;

/**
 * Created by bynn on 2018/2/11.
 */
public class JNIUtil {

    static {
        System.loadLibrary("JNISimpleUse");
    }

    public static native String helloFromC();

    public static native int add(int i, int j);

    public static native int encode(int pwd);

    public static native String encodeStr(String str, int len);

    public static native String decodeStr(String str, int len);

    public static native void transmit(int[] arry);

    public static native void callJava();
}
