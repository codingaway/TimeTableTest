package com.example.timetabletest;

import java.util.ArrayList;

/**
 * Created by casper on 31/03/15.
 *
 * This singleton class is used to hold app data
 * mainly the schedule list in the global context so that all activity have access them
 */
public class AppData {

    private static AppData instance;
    private static ArrayList<ArrayList<Session>> data;

    private AppData()
    {
        // Constructor hidden because this is a singleton
    }

    public static void initInstance()
    {
        if (instance == null)
        {
            // Create the instance
            instance = new AppData();
        }
    }

    public static AppData getInstance()
    {
        // Return the instance
        return instance;
    }

    public static ArrayList<ArrayList<Session>>  getData() {return data;}
    public static void setData(ArrayList<ArrayList<Session>>  data) {AppData.data = data;}
    public static boolean dataExist(){
        return AppData.data != null;
    }
}