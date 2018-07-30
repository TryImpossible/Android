package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import com.barry.basic.R;
import com.barry.basic.entity.City;
import com.barry.basic.entity.Sms;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlActivity extends Activity {

    public final static String TAG = "XmlActivity";
    private List<Sms> smsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        smsList = new ArrayList<Sms>();
        for (int i = 0; i < 10; i++) {
            Sms sms = new Sms("短信" + i, System.currentTimeMillis(), 1, "138438");
            smsList.add(sms);
        }
    }

    private List<City> cityList;

    public void click1(View v) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("<?xml version=\"1.0\" ecoding=\"utf-8\"?>");
//        sb.append("<smss>");
//        for (Sms sms : smsList) {
//            sb.append("<sms>");
//
//            sb.append("<body>");
//            sb.append(sms.getBody() + "<body>");
//            sb.append("</body>");
//
//            sb.append("<date>");
//            sb.append(sms.getDate());
//            sb.append("</date>");
//
//            sb.append("<type>");
//            sb.append(sms.getType());
//            sb.append("</type>");
//
//            sb.append("<address>");
//            sb.append(sms.getAddress());
//            sb.append("</address>");
//
//            sb.append("</sms>");
//        }
//        sb.append("</smss>");
//
//        File file = new File(Environment.getExternalStorageDirectory() + "/sms.xml");
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(sb.toString().getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        XmlSerializer xs = Xml.newSerializer();
        File file = new File(Environment.getExternalStorageDirectory(), "/sms2.xml");
        FileOutputStream fos;
        String enter = System.getProperty("line.separator");
        try {
            fos = new FileOutputStream(file);

            xs.setOutput(fos, "utf-8");

            xs.startDocument("utf-8", true);

            changeLine(xs, enter);
            xs.startTag(null, "smss");

            for (Sms sms : smsList) {
                changeLine(xs, enter);
                xs.startTag(null, "sms");

                changeLine(xs, enter);
                xs.startTag(null, "body");
                xs.text(sms.getBody() + "<body>");
                xs.endTag(null, "body");
                changeLine(xs, enter);

                xs.startTag(null, "type");
                xs.text(sms.getBody());
                xs.endTag(null, "type");
                changeLine(xs, enter);

                xs.startTag(null, "date");
                xs.text(sms.getBody());
                xs.endTag(null, "date");
                changeLine(xs, enter);

                xs.startTag(null, "address");
                xs.text(sms.getBody());
                xs.endTag(null, "address");
                changeLine(xs, enter);

                xs.endTag(null, "sms");
            }

            xs.endTag(null, "smss");

            xs.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeLine(XmlSerializer serializer, String enter){
        try{
            serializer.text(enter);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void click2(View v) {
//        不知道為什麼讀取不了
//        InputStream is = getClassLoader().getResourceAsStream("weather.xml");
//        InputStream is= getClass().getResourceAsStream("1.txt");
//        Log.i(TAG, is.toString());
//        InputStream is = getClassLoader().getResourceAsStream("weather.xml");
//        InputStream resourceAsStream = ClassLoader.getSystemResourceAsStream(getPackageName() + "/weather.txt");

        XmlPullParser xp = Xml.newPullParser();
        try {
            InputStream is = getAssets().open("weather.xml");
            xp.setInput(is, "utf-8");
            int type = xp.getEventType();
            City city = null;
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("weather".equals(xp.getName())) {
                            cityList = new ArrayList<City>();
                        } else if ("city".equals(xp.getName())) {
                            city = new City();
                        } else if ("name".equals(xp.getName())) {
                            String name = xp.nextText();
                            city.setName(name);
                        } else if ("temp".equals(xp.getName())) {
                            String temp = xp.nextText();
                            city.setTemp(temp);
                        } else if ("pm25".equals(xp.getName())) {
                            String pm25 = xp.nextText();
                            city.setPm25(pm25);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("city".equals(xp.getName())) {
                            cityList.add(city);
                        }
                        break;
                }
                type = xp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cityList != null) {
            for (City city : cityList) {
                Log.i(TAG, city.toString());
            }
        }

    }
}
