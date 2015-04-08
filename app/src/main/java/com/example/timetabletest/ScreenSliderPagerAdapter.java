package com.example.timetabletest;

/**
 * Created by casper on 18/03/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
public class ScreenSliderPagerAdapter extends FragmentStatePagerAdapter {

    //private int numberOfPages;
    private  ArrayList<ArrayList<Session>> list;

    public ScreenSliderPagerAdapter(FragmentManager fm, ArrayList<ArrayList<Session>> list) {
        super(fm);
        this.list = list;
        Log.d("LOG", "New ScreenPagerAdapter created!");
    }


    @Override
    public Fragment getItem(int position) {
        //Use position(which is page number to get a certain days Session)

        Log.d("LOG", "Getting a Fragment...");
        ArrayList<Session> todaysList = list.get(position);

        //instantiate a list adapter using todaysList
        SessionListAdapter listAdapter = new SessionListAdapter(todaysList);

        //instantiate a Pager fragment
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        fragment.setListAdapter(listAdapter);
        Log.d("LOG", "New list Fragment is created!");
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case(0): return "Monday";
            case(1): return "Tuesday";
            case(2): return "Wednesday";
            case(3): return "Thursday";
            case(4): return "Friday";
            case(5): return "Saturday";
            case(6): return "Sunday";
            default: return "error";
        }
    }
}