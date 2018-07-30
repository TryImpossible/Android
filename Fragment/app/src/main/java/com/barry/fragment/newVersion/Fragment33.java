package com.barry.fragment.newVersion;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.barry.fragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment33 extends Fragment {


    public Fragment33() {
        // Required empty public constructor
    }

    private MyListener myListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyListener) {
            myListener = (MyListener)context;
        } else {
            throw new IllegalArgumentException("Activity must implements MyListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment33, container, false);

        Button btn = (Button) v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((FragmentActivity)getActivity()).setText("123");
                myListener.callbak("來自Fragment");
            }
        });

        return v;
    }
}