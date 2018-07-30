package com.barry.fragment.oldVersion;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barry.fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment03 extends Fragment{


    public Fragment03() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment03, container, false);
    }

}
