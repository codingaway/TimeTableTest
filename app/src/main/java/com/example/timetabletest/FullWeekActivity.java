package com.example.timetabletest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Loaner on 25/03/2015.
 */
public class FullWeekActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_view_layout);

        TextView txtMon9 = (TextView)findViewById(R.id.txtMon9);
        TextView txtMon10 = (TextView)findViewById(R.id.txtMon10);
        TextView txtMon11 = (TextView)findViewById(R.id.txtMon11);
        TextView txtMon12 = (TextView)findViewById(R.id.txtMon12);
        TextView txtMon13 = (TextView)findViewById(R.id.txtMon13);
        TextView txtMon14 = (TextView)findViewById(R.id.txtMon14);
        TextView txtMon15 = (TextView)findViewById(R.id.txtMon15);
        TextView txtMon16 = (TextView)findViewById(R.id.txtMon16);
        TextView txtMon17 = (TextView)findViewById(R.id.txtMon17);

        TextView txtTue9 = (TextView)findViewById(R.id.txtTue9);
        TextView txtTue10 = (TextView)findViewById(R.id.txtTue10);
        TextView txtTue11 = (TextView)findViewById(R.id.txtTue11);
        TextView txtTue12 = (TextView)findViewById(R.id.txtTue12);
        TextView txtTue13 = (TextView)findViewById(R.id.txtTue13);
        TextView txtTue14 = (TextView)findViewById(R.id.txtTue14);
        TextView txtTue15 = (TextView)findViewById(R.id.txtTue15);
        TextView txtTue16 = (TextView)findViewById(R.id.txtTue16);
        TextView txtTue17 = (TextView)findViewById(R.id.txtTue17);

        TextView txtWed9 = (TextView)findViewById(R.id.txtWed9);
        TextView txtWed10 = (TextView)findViewById(R.id.txtWed10);
        TextView txtWed11 = (TextView)findViewById(R.id.txtWed11);
        TextView txtWed12 = (TextView)findViewById(R.id.txtWed12);
        TextView txtWed13 = (TextView)findViewById(R.id.txtWed13);
        TextView txtWed14 = (TextView)findViewById(R.id.txtWed14);
        TextView txtWed15 = (TextView)findViewById(R.id.txtWed15);
        TextView txtWed16 = (TextView)findViewById(R.id.txtWed16);
        TextView txtWed17 = (TextView)findViewById(R.id.txtWed17);

        TextView txtThur9 = (TextView)findViewById(R.id.txtThur9);
        TextView txtThur10 = (TextView)findViewById(R.id.txtThur10);
        TextView txtThur11 = (TextView)findViewById(R.id.txtThur11);
        TextView txtThur12 = (TextView)findViewById(R.id.txtThur12);
        TextView txtThur13 = (TextView)findViewById(R.id.txtThur13);
        TextView txtThur14 = (TextView)findViewById(R.id.txtThur14);
        TextView txtThur15 = (TextView)findViewById(R.id.txtThur15);
        TextView txtThur16 = (TextView)findViewById(R.id.txtThur16);
        TextView txtThur17 = (TextView)findViewById(R.id.txtThur17);

        TextView txtFri9 = (TextView)findViewById(R.id.txtFri9);
        TextView txtFri10 = (TextView)findViewById(R.id.txtFri10);
        TextView txtFri11 = (TextView)findViewById(R.id.txtFri11);
        TextView txtFri12 = (TextView)findViewById(R.id.txtFri12);
        TextView txtFri13 = (TextView)findViewById(R.id.txtFri13);
        TextView txtFri14 = (TextView)findViewById(R.id.txtFri14);
        TextView txtFri15 = (TextView)findViewById(R.id.txtFri15);
        TextView txtFri16 = (TextView)findViewById(R.id.txtFri16);
        TextView txtFri17 = (TextView)findViewById(R.id.txtFri17);

        ArrayList<ArrayList<Session>> weeklyList = readDataFromFile();

        for(int j = 0; j < weeklyList.size(); j++){
            ArrayList<Session> day = weeklyList.get(j);
            for(int k = 0; k < day.size(); k++){
                Session s = day.get(k);
                    switch(j) {
                        case(0):
                            switch(s.getStartTime()){
                                case("09:00"): txtMon9.setText(s.getModule()); txtMon9.isClickable(); break;
                                case("10:00"): txtMon10.setText(s.getModule()); txtMon10.isClickable(); break;
                                case("11:00"): txtMon11.setText(s.getModule()); txtMon11.isClickable(); break;
                                case("12:00"): txtMon12.setText(s.getModule()); txtMon12.isClickable(); break;
                                case("13:00"): txtMon13.setText(s.getModule()); txtMon13.isClickable(); break;
                                case("14:00"): txtMon14.setText(s.getModule()); txtMon14.isClickable(); break;
                                case("15:00"): txtMon15.setText(s.getModule()); txtMon15.isClickable(); break;
                                case("16:00"): txtMon16.setText(s.getModule()); txtMon16.isClickable(); break;
                                case("17:00"): txtMon17.setText(s.getModule()); txtMon17.isClickable(); break;
                            } break;
                        case(1):
                            switch(s.getStartTime()){
                                case("09:00"): txtTue9.setText(s.getModule()); txtTue9.isClickable(); break;
                                case("10:00"): txtTue10.setText(s.getModule()); txtTue10.isClickable(); break;
                                case("11:00"): txtTue11.setText(s.getModule()); txtTue11.isClickable(); break;
                                case("12:00"): txtTue12.setText(s.getModule()); txtTue12.isClickable(); break;
                                case("13:00"): txtTue13.setText(s.getModule()); txtTue13.isClickable(); break;
                                case("14:00"): txtTue14.setText(s.getModule()); txtTue14.isClickable(); break;
                                case("15:00"): txtTue15.setText(s.getModule()); txtTue15.isClickable(); break;
                                case("16:00"): txtTue16.setText(s.getModule()); txtTue16.isClickable(); break;
                                case("17:00"): txtTue17.setText(s.getModule()); txtTue17.isClickable(); break;
                            } break;
                        case(2):
                            switch(s.getStartTime()){
                                case("09:00"): txtWed9.setText(s.getModule()); txtWed9.isClickable(); break;
                                case("10:00"): txtWed10.setText(s.getModule()); txtWed10.isClickable(); break;
                                case("11:00"): txtWed11.setText(s.getModule()); txtWed11.isClickable(); break;
                                case("12:00"): txtWed12.setText(s.getModule()); txtWed12.isClickable(); break;
                                case("13:00"): txtWed13.setText(s.getModule()); txtWed13.isClickable(); break;
                                case("14:00"): txtWed14.setText(s.getModule()); txtWed14.isClickable(); break;
                                case("15:00"): txtWed15.setText(s.getModule()); txtWed15.isClickable(); break;
                                case("16:00"): txtWed16.setText(s.getModule()); txtWed16.isClickable(); break;
                                case("17:00"): txtWed17.setText(s.getModule()); txtWed17.isClickable(); break;
                            } break;
                        case(3):
                            switch(s.getStartTime()){
                                case("09:00"): txtThur9.setText(s.getModule()); txtThur9.isClickable(); break;
                                case("10:00"): txtThur10.setText(s.getModule()); txtThur10.isClickable(); break;
                                case("11:00"): txtThur11.setText(s.getModule()); txtThur11.isClickable(); break;
                                case("12:00"): txtThur12.setText(s.getModule()); txtThur12.isClickable(); break;
                                case("13:00"): txtThur13.setText(s.getModule()); txtThur13.isClickable(); break;
                                case("14:00"): txtThur14.setText(s.getModule()); txtThur14.isClickable(); break;
                                case("15:00"): txtThur15.setText(s.getModule()); txtThur15.isClickable(); break;
                                case("16:00"): txtThur16.setText(s.getModule()); txtThur16.isClickable(); break;
                                case("17:00"): txtThur17.setText(s.getModule()); txtThur17.isClickable(); break;
                            } break;
                        case(4):
                            switch(s.getStartTime()){
                                case("09:00"): txtFri9.setText(s.getModule()); txtFri9.isClickable(); break;
                                case("10:00"): txtFri10.setText(s.getModule()); txtFri10.isClickable(); break;
                                case("11:00"): txtFri11.setText(s.getModule()); txtFri11.isClickable(); break;
                                case("12:00"): txtFri12.setText(s.getModule()); txtFri12.isClickable(); break;
                                case("13:00"): txtFri13.setText(s.getModule()); txtFri13.isClickable(); break;
                                case("14:00"): txtFri14.setText(s.getModule()); txtFri14.isClickable(); break;
                                case("15:00"): txtFri15.setText(s.getModule()); txtFri15.isClickable(); break;
                                case("16:00"): txtFri16.setText(s.getModule()); txtFri16.isClickable(); break;
                                case("17:00"): txtFri17.setText(s.getModule()); txtFri17.isClickable(); break;
                            } break;
                        default: break;
                    }
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(FullWeekActivity.this, ScreenSlidePagerActivity.class));
        }
    }

    public void onClick(View v){
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
    }

    private ArrayList<ArrayList<Session>> readDataFromFile()
    {
        String fileName = "data.dat";
        ArrayList<ArrayList<Session>> weeklyList = null;
        File file = new File(this.getFilesDir(),fileName);
        if(file.exists())
        {
            //We have data file so read data from it
            try {
                //Object input stream
                ObjectInputStream ois = new ObjectInputStream(openFileInput("data.dat"));
                weeklyList = (ArrayList<ArrayList<Session>>) (ois.readObject());
                ois.close();
                Log.d("LOG", "File read successfully!");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            Log.d("LOG", "File not found!");
        }
        return weeklyList;
    }
}
