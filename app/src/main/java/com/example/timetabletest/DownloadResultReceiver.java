package com.example.timetabletest;

/**
 * Created by casper on 18/03/15.
 *
 * If for some reason Activity is destroyed while service is running and its sending feedback to the Activity.
 * This might cause some problem as the receiver is not available anymore.
 * This will cause any messages sent to this ResultReceiver to end up nowhere after the Activity has been destroyed
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class DownloadResultReceiver extends ResultReceiver {

    private Receiver mReceiver;

    public DownloadResultReceiver(Handler handler) {
        super(handler);
    }

    //If a class implements "Receiver" Interface it can set it-self as a receiver
    //and This class can forward ReceiveResult to that.
    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    /* Receiver Interface */
    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) //forward ReceiveResult to the object that is still available
        {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
