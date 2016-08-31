package com.paularagones.quandoocodechallenge.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by paul.aragones on 7/27/15.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = NotificationReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(LOG_TAG, " on Receive");
        Toast.makeText(context, "Tables Refreshed! ", Toast.LENGTH_LONG).show();

        HashMap<Integer,String> tableCellMap = new HashMap<>();
        Gson gson = new Gson();
        String tableCellMapString = gson.toJson(tableCellMap);
        SharedPreferences prefs = context.getSharedPreferences("test", context.MODE_PRIVATE);
        prefs.edit().putString("tableCellMapString", tableCellMapString).apply();


        EventBus eventBus = EventBus.getDefault();
        eventBus.postSticky(tableCellMap);

    }
}
