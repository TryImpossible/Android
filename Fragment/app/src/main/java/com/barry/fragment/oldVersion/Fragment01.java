package com.barry.fragment.oldVersion;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barry.fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment01 extends Fragment {


    public Fragment01() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("01onCreateView");
        return inflater.inflate(R.layout.fragment_fragment01, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        System.out.println("01onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("01onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        System.out.println("01onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println("01onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println("01onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println("01onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println("01onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        System.out.println("01onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("01onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println("01onDetach");
        super.onDetach();
    }

}
