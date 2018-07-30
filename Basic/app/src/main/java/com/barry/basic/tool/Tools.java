package com.barry.basic.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bynn on 2018/1/22.
 */
public class Tools {
    public static String getTextFromStream(InputStream is) {
        byte[] b = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        try {
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.close();
            return new String(bos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return "解析報錯";
        }
    }
}
