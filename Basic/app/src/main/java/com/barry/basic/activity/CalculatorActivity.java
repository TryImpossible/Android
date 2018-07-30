package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barry.basic.R;

public class CalculatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xml布局
//        setContentView(R.layout.activity_calculator);

        // 代码布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setPadding(dp2px(2), dp2px(5), dp2px(2), dp2px(5));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(linearLayout);

        LinearLayout ll_first = new LinearLayout(this);
        ll_first.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_first.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(ll_first);
        TextView tv_screen = new TextView(this);
        tv_screen.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px((44))));
        tv_screen.setBackgroundResource(R.drawable.border);
        ll_first.addView(tv_screen);


        LinearLayout ll_second = new LinearLayout(this);
        ll_first.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_first.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(ll_second);
        Button btn_mc = new Button(this);
        btn_mc.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_mc.setText("mc");
        ll_second.addView(btn_mc);
        Button btn_mAdd = new Button(this);
        btn_mAdd.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_mAdd.setText("m+");
        ll_second.addView(btn_mAdd);
        Button btn_mMinus = new Button(this);
        btn_mMinus.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_mMinus.setText("m-");
        ll_second.addView(btn_mMinus);
        Button btn_mr = new Button(this);
        btn_mr.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_mr.setText("mr");
        ll_second.addView(btn_mr);

        LinearLayout ll_thrid = new LinearLayout(this);
        ll_thrid.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_thrid.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(ll_thrid);
        Button btn_c = new Button(this);
        btn_c.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_c.setText("C");
        ll_thrid.addView(btn_c);
        Button btn_mAddOrMinus = new Button(this);
        btn_mAddOrMinus.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_mAddOrMinus.setText("m+");
        ll_thrid.addView(btn_mAddOrMinus);
        Button btn_divide = new Button(this);
        btn_divide.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_divide.setText("/");
        ll_thrid.addView(btn_divide);
        Button btn_multiply = new Button(this);
        btn_multiply.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_multiply.setText("*");
        ll_thrid.addView(btn_multiply);

        LinearLayout ll_fourth = new LinearLayout(this);
        ll_fourth.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_fourth.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(ll_fourth);
        Button btn_7 = new Button(this);
        btn_7.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_7.setText("7");
        ll_fourth.addView(btn_7);
        Button btn_8 = new Button(this);
        btn_8.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_8.setText("8");
        ll_fourth.addView(btn_8);
        Button btn_9 = new Button(this);
        btn_9.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_9.setText("9");
        ll_fourth.addView(btn_9);
        Button btn_minus = new Button(this);
        btn_minus.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_minus.setText("-");
        ll_fourth.addView(btn_minus);

        LinearLayout ll_fifth = new LinearLayout(this);
        ll_fifth.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_fifth.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(ll_fifth);
        Button btn_4 = new Button(this);
        btn_4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_4.setText("4");
        ll_fifth.addView(btn_4);
        Button btn_5 = new Button(this);
        btn_5.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_5.setText("5");
        ll_fifth.addView(btn_5);
        Button btn_6 = new Button(this);
        btn_6.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_6.setText("6");
        ll_fifth.addView(btn_6);
        Button btn_add = new Button(this);
        btn_add.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_add.setText("+");
        ll_fifth.addView(btn_add);


        LinearLayout ll_twoPart = new LinearLayout(this);
        ll_twoPart.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_twoPart.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(ll_twoPart);

        LinearLayout ll_left = new LinearLayout(this);
        ll_left.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 3.0f));
        ll_left.setOrientation(LinearLayout.VERTICAL);
        ll_twoPart.addView(ll_left);

        LinearLayout ll_top = new LinearLayout(this);
        ll_top.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_top.setOrientation(LinearLayout.HORIZONTAL);
        ll_left.addView(ll_top);
        Button btn_1 = new Button(this);
        btn_1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_1.setText("1");
        ll_top.addView(btn_1);
        Button btn_2 = new Button(this);
        btn_2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_2.setText("2");
        ll_top.addView(btn_2);
        Button btn_3 = new Button(this);
        btn_3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_3.setText("3");
        ll_top.addView(btn_3);

        LinearLayout ll_bottom = new LinearLayout(this);
        ll_bottom.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_bottom.setOrientation(LinearLayout.HORIZONTAL);
        ll_left.addView(ll_bottom);
        Button btn_0 = new Button(this);
        btn_0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_0.setText("0");
        ll_bottom.addView(btn_0);
        Button btn_spot = new Button(this);
        btn_spot.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        btn_spot.setText(".");
        ll_bottom.addView(btn_spot);

        Button btn_right = new Button(this);
        btn_right.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
        btn_right.setText("=");
        btn_right.setGravity(Gravity.CENTER);
        ll_twoPart.addView(btn_right);

    }

    private int dp2px(int dp) {
        float scale = this.getResources().getDisplayMetrics().density;
        System.out.println((int) (dp * scale + 0.5f));
        return (int) (dp * scale + 0.5f);
    }
}
