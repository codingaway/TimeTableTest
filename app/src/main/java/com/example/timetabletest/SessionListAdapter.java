package com.example.timetabletest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by casper on 18/03/15.
 */
public class SessionListAdapter extends BaseAdapter {

    private ArrayList<Session> sessionList;

    public SessionListAdapter(ArrayList<Session> sessionList)
    {
        super();
        this.sessionList = sessionList;
    }
    @Override
    public int getCount() {
        return sessionList.size();
    }

    @Override
    public Object getItem(int position) {
        return sessionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.session_item_layout, parent, false);
        }

        Session session = sessionList.get(position);

        TextView textStartTime = (TextView)convertView.findViewById(R.id.start_time);
        TextView textEndTime = (TextView)convertView.findViewById(R.id.end_time);
        TextView textModule = (TextView)convertView.findViewById(R.id.module_code);
        TextView textRoom = (TextView)convertView.findViewById(R.id.room_code);
        TextView textType = (TextView)convertView.findViewById(R.id.session_type);

        textStartTime.setText(session.getStartTime());
        textEndTime.setText(session.getEndTime());
        textModule.setText(session.getModule());
        textRoom.setText(session.getRoomCode());
        textType.setText(session.getSessionType());

        return convertView;
    }
}
