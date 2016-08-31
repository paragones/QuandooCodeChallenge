package com.paularagones.quandoocodechallenge.Services;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by paul.aragones on 6/16/15.
 */
public class CalendarUtil {
    private static final String LOG_TAG = CalendarUtil.class.getSimpleName();

    public static void setNotificationReminder(Activity activity) {

        SharedPreferences prefs = activity.getSharedPreferences("test", activity.MODE_PRIVATE);
        boolean isAlreadySet = prefs.getBoolean("isAlreadySet", false);

        if (!isAlreadySet) {
            prefs.edit().putBoolean("isAlreadySet", true).apply();

            Log.e(LOG_TAG, " on setNotificationReminder");
            Calendar calendar;
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            long tenMinutes = 10 * 60 * 1000;
//        calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 10);
            Intent intent1 = new Intent(activity, NotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) activity.getSystemService(activity.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), tenMinutes, pendingIntent);
        }
    }



}
