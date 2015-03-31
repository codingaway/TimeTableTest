package com.example.timetabletest;

import android.app.ProgressDialog;
//import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
//import android.hardware.SensorManager;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.widget.Toast;
//import android.support.v4.view.PagerTitleStrip;

import java.io.File;
//import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by casper on 18/03/15.
 */
public class ScreenSlidePagerActivity extends ActionBarActivity{

    private DownloadResultReceiver mReceiver;
    private ProgressDialog mProgressDialog;
    private ViewPager viewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        ArrayList<ArrayList<Session>> weeklyList = readDataFromFile();

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
        switch(weekDay){
            case("Monday"):     position = 0; break;
            case("Tuesday"):    position = 1; break;
            case("Wednesday"):  position = 2; break;
            case("Thursday"):   position = 3; break;
            case("Friday"):     position = 4; break;
            case("Saturday"):   position = 5; break;
            //case("Sunday"):     position = 6; break;
        }
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first page, allow the system to handle the
            // Back button.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous page.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ScreenSlidePagerActivity.this, FullWeekActivity.class));
        }
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

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch(id){
            case R.id.action_settings: Intent settingsIntent =  new Intent(ScreenSlidePagerActivity.this, SettingsActivity.class); startActivity(settingsIntent);break;
            case R.id.preferences: Toast.makeText(ScreenSlidePagerActivity.this, "Preferences was selected", Toast.LENGTH_LONG).show(); break;
            case R.id.customise: Toast.makeText(ScreenSlidePagerActivity.this, "Customise was selected", Toast.LENGTH_LONG).show(); break;
            case R.id.update: SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);SharedPreferences.Editor editor = sharedPref.edit();editor.putString("studentID", "");editor.apply(); break;
            case R.id.planner: Toast.makeText(ScreenSlidePagerActivity.this, "Add a meeting/agenda was selected", Toast.LENGTH_LONG).show(); break;
            case R.id.help: Toast.makeText(ScreenSlidePagerActivity.this, "Help was selected", Toast.LENGTH_LONG).show(); break;
            default: Toast.makeText(ScreenSlidePagerActivity.this, "Default was selected", Toast.LENGTH_LONG).show(); break;
        }
        return super.onOptionsItemSelected(item);
    }

   @Override
    protected void onDestroy(){
       super.onDestroy();
       //myOrientationEventListener.disable();
   }
}
