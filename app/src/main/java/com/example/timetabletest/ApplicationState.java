package com.example.timetabletest;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by casper on 31/03/15.
 */
public class ApplicationState extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        initAppData();

    }

    protected void initAppData()
    {
        // Initialize the instance of MySingleton
        AppData.initInstance();

        String fileName = "data.dat";
        File file = new File(this.getFilesDir(),fileName);
        if(file.exists())
        {
            ArrayList<ArrayList<Session>> weeklyList = readDataFromFile(file);

            if(weeklyList != null) //Save User data to the AppData static instance
                AppData.setData(weeklyList);
        }
    }

    private ArrayList<ArrayList<Session>> readDataFromFile(File file)
    {
        ArrayList<ArrayList<Session>> weeklyList = null;
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
        return weeklyList;
    }
}
