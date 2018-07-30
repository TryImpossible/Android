package com.barry.customcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.barry.customcontrol.R.id.iv_menu;

public class YouKuMenuActivity extends Activity implements View.OnClickListener{

    private RelativeLayout rlLevel1, rlLevel2, rlLevel3;
    private boolean isLevel1Show = true;
    private boolean isLevel2Show = true;
    private boolean isLevel3Show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_ku_menu);
        ImageView ivHome = (ImageView) findViewById(R.id.iv_home);
        ImageView ivMenu = (ImageView) findViewById(iv_menu);

        rlLevel1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rlLevel2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rlLevel3 = (RelativeLayout) findViewById(R.id.rl_level3);

        ivHome.setOnClickListener(this);
        ivMenu.setOnClickListener(this);

        //為了避免第三層佈局將一二層事件攔截掉，需要在佈局文件中最先註冊第三層，最後註冊第一層
        rlLevel3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home:
                System.out.println("home clicked");
                if (isLevel2Show) {
                    Tools.hideView(rlLevel2); //隱藏第二層佈局
                    isLevel2Show = false;

                    if (isLevel3Show) { //如果發現第三次也展現，也需要隱藏
                        Tools.hideView(rlLevel3, 200); //動畫昝200毫秒再運行
                        isLevel3Show = false;
                    }
                } else {
                    Tools.showView(rlLevel2);
                    isLevel2Show = true;
                }
                break;
            case R.id.iv_menu:
                System.out.println("menu clicked");
                if (isLevel3Show) {
                    Tools.hideView(rlLevel3);
                    isLevel3Show = false;
                } else {
                    Tools.showView(rlLevel3);
                    isLevel3Show = true;
                }
                break;
            default:
                break;
        }
    }
}
