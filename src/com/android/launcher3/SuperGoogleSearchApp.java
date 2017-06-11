package com.android.launcher3;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.widget.RemoteViews;

public class SuperGoogleSearchApp { //a
    static long VALIDITY_DURATION = 7200000;
    public final RemoteViews mRemoteViews; //bs
    public final int gsaVersion; //bt
    public final long gsaUpdateTime; //bu
    public final long publishTime; //bv

    public SuperGoogleSearchApp(Context context, RemoteViews remoteViews) { //a
        PackageInfo packageInfo = null;
        mRemoteViews = remoteViews;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.google.android.googlequicksearchbox", 0);
        } catch (NameNotFoundException e) {
        }
        if (packageInfo != null) {
            gsaUpdateTime = packageInfo.lastUpdateTime;
            gsaVersion = packageInfo.versionCode;
        } else {
            gsaUpdateTime = 0;
            gsaVersion = 0;
        }
        publishTime = SystemClock.uptimeMillis();
    }

    public SuperGoogleSearchApp(Bundle bundle) { //a
        gsaVersion = bundle.getInt("gsa_version", 0);
        gsaUpdateTime = bundle.getLong("gsa_update_time", 0);
        publishTime = bundle.getLong("publish_time", 0);
        mRemoteViews = bundle.getParcelable("views");
    }

    public long validity() { //aY
        return (VALIDITY_DURATION + publishTime) - SystemClock.uptimeMillis();
    }

    public Bundle getBundle() { //aZ
        final Bundle bundle = new Bundle();
        bundle.putInt("gsa_version", this.gsaVersion);
        bundle.putLong("gsa_update_time", this.gsaUpdateTime);
        bundle.putLong("publish_time", this.publishTime);
        bundle.putParcelable("views", (Parcelable)this.mRemoteViews);
        return bundle;
    }
}