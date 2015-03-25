package com.example.timetabletest;

//import android.support.v4.view.ViewPager;
import java.util.*;
//import java.io.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;



/**
 * Created by Josh on 22/02/2015.
 */
public class Login extends ActionBarActivity implements DownloadResultReceiver.Receiver {

    private final String fileName = "data.dat";
    private DownloadResultReceiver mReceiver;
    private ProgressDialog mProgressDialog;
    private ArrayList<ArrayList<Session>> weeklyList;


    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        input = (EditText) findViewById(R.id.idInput);
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String userID = input.getText().toString();

                        if (userID.length() == 0 || userID.length() != 8) {
                            //using R.string.thestringname references it from the strings.xml so no hard-coding text
                            Toast.makeText(Login.this, R.string.login_error, Toast.LENGTH_LONG).show();
                            //Add further validation for student ID
                            /*First 2 digits = Year of application
                              Next 5 digits = Sequential number
                              Last digit = A MOD 11 check digit*/
                        } else {

                            //Stores the Student ID (userID) into shared preferences
                            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("studentID", userID);
                            editor.apply();

                            //Toast.makeText(Login.this, "Starting background processing...", Toast.LENGTH_LONG).show();

                            // register a receiver for Download IntentService
                            mReceiver = new DownloadResultReceiver(new Handler());
                            mReceiver.setReceiver(Login.this);

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

                            //Need a check/splash screen to know when service is complete before loading MainActivity for the first time
//
//                            Intent secondIntent = new Intent(Login.this, MainActivity.class);
//                            startActivity(secondIntent);

                        }
                    }
                }
        );


    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        // here we receive the status result from DownloadDataIntentService

        switch (resultCode) {
            case DownloadDataIntentService.STATUS_RUNNING: {
                //show progress

                //Toast.makeText(this, "Running", Toast.LENGTH_LONG).show();
            }
            break;
            case DownloadDataIntentService.STATUS_FINISHED: {
                mProgressDialog.dismiss();
                Toast.makeText(this, "New data saved!", Toast.LENGTH_LONG).show();


                //We have data file; Start Screen slider Activity
                Intent secondIntent = new Intent(Login.this, ScreenSlidePagerActivity.class);
                startActivity(secondIntent);

            }
            break;
            case DownloadDataIntentService.STATUS_ERROR: {
                // handle the error;
                mProgressDialog.dismiss();
                Toast.makeText(this, "Error reading data from the server, try again!", Toast.LENGTH_LONG).show();
            }
            break;
        }

    }
}