package com.example.timetabletest;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;



/**
 * Created by Josh on 22/02/2015.
 */
public class Login extends ActionBarActivity implements DownloadResultReceiver.Receiver{

    private DownloadResultReceiver mReceiver;
    private ProgressDialog mProgressDialog;
    private SharedPreferences sharedPref;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String studentID = sharedPref.getString("studentID", "");


        if(studentID == "")
        {
            setContentView(R.layout.homescreen);
            editText = (EditText)findViewById(R.id.idInput);
            Button button = (Button)findViewById(R.id.loginBtn);
            button.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            String userID = editText.getText().toString();

                            if(userID.length() == 0 || (userID.length() != 8 && userID.length() != 7)){
                                Toast.makeText(Login.this, R.string.login_error,Toast.LENGTH_LONG).show();
                            }
                            else {

                                //Stores the Student ID (userID) into shared preferences

                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("studentID", userID);
                                editor.apply();
                                startDownloadService();
                            }
                        }
                    }
            );


        }
        else
        {
            setContentView(R.layout.splash_screen);
            startDownloadService();
        }


    }

    private void startDownloadService()
    {
        // register a receiver for Download IntentService
        mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(Login.this);//Set Result receiver to this Activity

        // start IntentService
        final Intent intent = new Intent(Login.this, DownloadDataIntentService.class);
        intent.putExtra("receiver", mReceiver);//Send receiver as Intent Extra
        startService(intent);

        //Show progress bar
        mProgressDialog = new ProgressDialog(Login.this);
        mProgressDialog.setTitle("Downloading data from server");
        mProgressDialog.setMessage("Download in progress...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        // here we receive the status result from DownloadDataIntentService

        switch (resultCode) {
            case DownloadDataIntentService.STATUS_RUNNING: {
            }
            break;

            case DownloadDataIntentService.STATUS_FINISHED:
            {
                mProgressDialog.dismiss();
                Toast.makeText(this, "New data saved!", Toast.LENGTH_LONG).show();

                //Read user data from data.dat file and load them into AppData
                AppData.setData(readDataFromFile());

                //We have data file; Start Screen slider Activity
                Intent secondIntent = new Intent(Login.this, ScreenSlidePagerActivity.class);
                startActivity(secondIntent);
                finish();
            }
            break;

            case DownloadDataIntentService.STATUS_ERROR:
            {
                // handle the error;
                mProgressDialog.dismiss();
                Toast.makeText(this, "Error reading data from the server, try again!", Toast.LENGTH_LONG).show();
            }
            break;

            case DownloadDataIntentService.NO_DATA_FOUND:
            {
                mProgressDialog.dismiss();
                Toast.makeText(this, "No data found for this ID", Toast.LENGTH_LONG).show();
            }
            break;
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
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return weeklyList;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
