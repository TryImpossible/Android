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
public class Fragment02 extends Fragment {


    public Fragment02() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("02onCreateView");
        return inflater.inflate(R.layout.fragment_fragment02, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        System.out.println("02onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("02onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        System.out.println("02onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println("02onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println("02onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println("02onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println("02onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        System.out.println("02onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("02onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println("02onDetach");
        super.onDetach();
    }

}
