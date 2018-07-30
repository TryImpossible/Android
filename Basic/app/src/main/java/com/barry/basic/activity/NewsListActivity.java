package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.barry.basic.R;
import com.barry.basic.entity.News;
import com.loopj.android.image.SmartImageView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends Activity {

    private List<News> newsList;

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                ListView lv = (ListView) findViewById(R.id.lv);
                lv.setAdapter(new MyAdapter());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                getNewsInfo();
            }
        });
        t.start();
    }

    public void getNewsInfo() {
        final String path = "http://192.168.16.84:8080/android-basic/news.xml";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);

//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("Content-Length", "100");
//            conn.setDoOutput(true);
//            OutputStream os = conn.getOutputStream();
//            os.write("123".getBytes());
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                resolveXml(is);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resolveXml(InputStream is) {
        XmlPullParser xp = Xml.newPullParser();
        try {
            xp.setInput(is, "utf-8");
            int type = xp.getEventType();
            News news = null;
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("newslist".equals(xp.getName())) {
                            newsList = new ArrayList<News>();
                        } else if ("news".equals(xp.getName())) {
                            news = new News();
                        } else if ("title".equals(xp.getName())) {
                            news.setTitle(xp.nextText());
                        } else if ("detail".equals(xp.getName())) {
                            news.setDetail(xp.nextText());
                        } else if ("comment".equals(xp.getName())) {
                            news.setComment(xp.nextText());
                        } else if ("image".equals(xp.getName())) {
                            news.setImageUrl(xp.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("news".equals(xp.getName())) {
                            newsList.add(news);
                        }
                        break;
                }
                type = xp.next();
            }
            handler.sendEmptyMessage(1);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsList != null ? newsList.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return newsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder mHolder = null;
            if (view == null) {
                view = View.inflate(NewsListActivity.this, R.layout.item_listview3, null);

                mHolder = new ViewHolder();
                mHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
                mHolder.tv_detail = (TextView) view.findViewById(R.id.tv_detail);
                mHolder.tv_comment = (TextView) view.findViewById(R.id.tv_comment);
                mHolder.siv = (SmartImageView) view.findViewById(R.id.siv);

                view.setTag(mHolder);

            } else {
                view = convertView;
                mHolder = (ViewHolder) view.getTag();
            }

            News news = newsList.get(position);
            mHolder.tv_title.setText(news.getTitle());
            mHolder.tv_detail.setText(news.getDetail());
            mHolder.tv_comment.setText(news.getComment() + "條評論");
            mHolder.siv.setImageUrl(news.getImageUrl());
            return view;
        }

        class ViewHolder {
            TextView tv_title;
            TextView tv_detail;
            TextView tv_comment;
            SmartImageView siv;
        }
    }

}
