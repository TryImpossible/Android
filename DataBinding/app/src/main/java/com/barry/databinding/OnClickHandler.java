package com.barry.databinding;

import android.view.View;
import android.widget.Toast;

public class OnClickHandler {

    public void onClickFriend(View view) {
        Toast.makeText(view.getContext(), "onClickFriend", Toast.LENGTH_SHORT).show();
    }
}
