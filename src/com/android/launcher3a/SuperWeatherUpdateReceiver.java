package com.android.launcher3a;

import android.widget.RemoteViews;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class SuperWeatherUpdateReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        SuperWeatherListener.getInstance(context).bH((RemoteViews)intent.getParcelableExtra("com.google.android.apps.nexuslauncher.weather_view"));
    }
}
