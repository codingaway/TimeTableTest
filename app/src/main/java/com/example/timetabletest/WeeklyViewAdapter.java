package com.example.timetabletest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.*;

/**
 * Created by casper on 31/03/15.
 */
public class WeeklyViewAdapter extends BaseAdapter {


    private final int NUMBER_OF_CELL = 70;

    private final String[] timeSlot ={"", "09:00", "10:00", "11:00", "12:00", "13:00",
            "14:00", "15:00","16:00", "17:00"};
    private final String[] weekDay = {"", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private ArrayList<ArrayList<Session>> list;
    private Context mContext;



    public WeeklyViewAdapter(Context c)

    {
        super();
        list = AppData.getData();
        mContext = c;
    }


    @Override
    public int getCount() {
        return NUMBER_OF_CELL;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView = new TextView(mContext);

        //Our GridView has 70 cells
        //Get Row and Col number based on position number
        int row = position / 7;
        int col = position % 7;


        //textView.setText(getCellData(row, col));
        //textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setGravity(Gravity.CENTER);



        if(row == 0 && col == 0) {
            textView.setText("");
        }
        else if(row == 0) {
            textView.setText(weekDay[col]);
            textView.setBackgroundColor(Color.parseColor("#93433E"));
            textView.setTextColor(Color.WHITE);
        }
        else if(col == 0) {
            textView.setText(timeSlot[row]);
            textView.setBackgroundColor(Color.parseColor("#93433E"));
            textView.setTextColor(Color.WHITE);
        }
        else
        {
            ArrayList<Session> daysList = list.get(col -1); //col is non-zero here(1-6)
            for(Session s: daysList)
            {
                if(timeSlot[row].compareTo(s.getStartTime()) == 0) {
                    textView.setText(s.getModule());
                    textView.setBackgroundColor(Color.parseColor("#54940D"));
                    textView.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        }

        return textView;
    }
//
//    private String getCellData(int row, int col)
//    {
//        if(row == 0 && col == 0)
//            return "";
//        else if(row == 0)
//            return weekDay[col];
//        else if(col == 0)
//            return timeSlot[row];
//        else
//        {
//            ArrayList<Session> daysList = list.get(col -1); //col is non-zero here(1-6)
//            for(Session s: daysList)
//            {
//                if(timeSlot[row].compareTo(s.getStartTime()) == 0)
//                    return s.getModule();
//            }
//        }
//        return null;
//    }
}
