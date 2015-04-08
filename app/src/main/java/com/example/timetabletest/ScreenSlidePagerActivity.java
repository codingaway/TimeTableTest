package com.example.timetabletest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by casper on 18/03/15.
 */
public class ScreenSlidePagerActivity extends ActionBarActivity{
    private ViewPager viewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        if(AppData.dataExist()) {
            ArrayList<ArrayList<Session>> weeklyList = AppData.getData();

            // Instantiate a ViewPager and a PagerAdapter.
            viewPager = (ViewPager) findViewById(R.id.pager);
            mPagerAdapter = new ScreenSliderPagerAdapter(getSupportFragmentManager(), weeklyList);
            viewPager.setAdapter(mPagerAdapter);
            Log.d("LOG", "ViewPager Loaded!");

            //Get Day of the week and change to integer value representing the page fragment to load in adapter
            String weekDay;
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            weekDay = dayFormat.format(calendar.getTime());

            int position = 0;
            switch (weekDay) {
                case ("Monday"): position = 0; break;
                case ("Tuesday"): position = 1; break;
                case ("Wednesday"): position = 2; break;
                case ("Thursday"): position = 3; break;
                case ("Friday"): position = 4; break;
                case ("Saturday"): position = 5; break;
                case ("Sunday"): position = 5; break;//There is no SUNDAY go back to Saturday
            }
            viewPager.setCurrentItem(position);
        }
        else
        {
            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("studentID", "");
            editor.apply();
            startActivity(new Intent(this, Login.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen and starts new activity for landscape view
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            startActivity(new Intent(ScreenSlidePagerActivity.this, WeeklyView.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
            {
                Intent settingsIntent = new Intent(ScreenSlidePagerActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
            break;
//            case R.id.preferences: Toast.makeText(ScreenSlidePagerActivity.this, "Preferences was selected", Toast.LENGTH_LONG).show(); break;
            case R.id.logout:
            {
                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("studentID", "");
                editor.apply();
                File dataFile = new File(getFilesDir(), "data.dat");
                boolean success = dataFile.delete();
                if(success)
                    Toast.makeText(this, "Data deleted", Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(this, Login.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                this.startActivity(mainIntent);
                finish();
            }
            break;
            case R.id.update:
                {
                    /* Create an Intent that will start the Menu-Activity. */
                    AppData.setData(null);
                    Intent mainIntent = new Intent(this, Login.class);
                    this.startActivity(mainIntent);
                    finish();
                }
                 break;
            case R.id.action_map:
            {
                //setImageResource(R.drawable.map);
                startActivity(new Intent(this, ImageActivity.class));
            }
            break;
//            case R.id.planner: Toast.makeText(ScreenSlidePagerActivity.this, "Add a meeting/agenda was selected", Toast.LENGTH_LONG).show(); break;
//            case R.id.help: Toast.makeText(ScreenSlidePagerActivity.this, "Help was selected", Toast.LENGTH_LONG).show(); break;
            default: Toast.makeText(ScreenSlidePagerActivity.this, "Default was selected", Toast.LENGTH_LONG).show(); break;
        }
        return super.onOptionsItemSelected(item);
    }

}
