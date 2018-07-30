package com.barry.fragment.newVersion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barry.fragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment11 extends Fragment {


    public Fragment11() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment11, container, false);
        TextView tv = (TextView) v.findViewById(R.id.tv);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tv.setText(bundle.getString("str").toString());
        }

        return v;
    }

}
