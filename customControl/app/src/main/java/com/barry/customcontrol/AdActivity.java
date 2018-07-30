package com.barry.customcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdActivity extends Activity {

    private ViewPager mViewPager;
    private TextView tvTitile;
    private LinearLayout llContainer;

    private int[] mImageIds = new int[] { R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d, R.drawable.e };

    // 图片标题集合
    private final String[] mImageDes = { "巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" };

    private int mPreviousPos; //上一个页面位置

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int currentItem = mViewPager.getCurrentItem(); //获取当前页面位置
            mViewPager.setCurrentItem(++currentItem); //跳到下一个页面

            //继续发送延时2秒的消息，形成类似递归的效果，使广告一直循环切换
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        tvTitile = (TextView) findViewById(R.id.tv_title);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);

        mViewPager.setAdapter(new MyAdapter()); //給viewpager設置數據
//        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        mViewPager.setCurrentItem(mImageIds.length * 10000);
        //页面滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动过程中
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面被选中
            @Override
            public void onPageSelected(int position) {
                int pos = position % mImageIds.length;
                tvTitile.setText(mImageDes[pos]); //更新新闻标题

                // 更新小圆点
                llContainer.getChildAt(pos).setEnabled(true); //将选中的页面的圆点设置为红色
                //将上一个圆点变为灰色
                llContainer.getChildAt(mPreviousPos).setEnabled(false);

                //更新上一个页面位置
                mPreviousPos = pos;
            }

            //滑动状态变化
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvTitile.setText(mImageDes[0]); //初始化新聞標題

        //動態添加5個小圓點
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.shape_point_selector);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i != 0) { //从第2个圆点开始设置左边距，保证圆点之间的间距
                params.leftMargin = 6;
                view.setEnabled(false); //设置不可用，变为灰色圆点
            }
            view.setLayoutParams(params);
            llContainer.addView(view);
        }

        //延时2秒更新广告条的消息
        handler.sendEmptyMessageDelayed(0, 2000);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null); //清除所有消息和Runnable对象
                        break;
                    case MotionEvent.ACTION_UP:
                        //继续轮播广告
                        handler.sendEmptyMessageDelayed(0, 2000);
                        break;

                    default:
                        break;
                }
                return false; //返回false,让viewpager原生触摸效果正常运行
            }
        });

    }

    private class MyAdapter extends PagerAdapter {

        //返回item的個數
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        //判斷當前要展示的View和返回的object是否是一個對象，如果是，才展示
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 類似getView方法，初始化每個item的佈局，viewpager默認自動加載前一張和後一張圖片，保證始終保持3張圖片
        // 剩餘的都需要銷燬
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //0,1, 5->0, 6->1, 10->0
            int pos = position % mImageIds.length;
            ImageView view = new ImageView(getApplicationContext());
            view.setBackgroundResource(mImageIds[pos]);

            //將item的佈局添加給容器
            container.addView(view);
            System.out.println("初始化item..." + pos);

            return view;
        }

        // item銷燬的囘調方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //從容器中移除佈局對象
            container.removeView((View)object);
            System.out.println("銷燬item..." + position);
        }
    }
}
