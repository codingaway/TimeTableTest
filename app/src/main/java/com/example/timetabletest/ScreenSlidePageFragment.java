package com.example.timetabletest;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by casper on 18/03/15.
 */
public class ScreenSlidePageFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }
}
