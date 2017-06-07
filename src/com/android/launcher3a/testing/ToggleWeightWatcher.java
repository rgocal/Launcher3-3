package com.android.launcher3a.testing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.launcher3a.Launcher;
import com.android.launcher3a.LauncherAppState;
import com.android.launcher3a.Utilities;
import com.android.launcher3a.util.TestingUtils;

public class ToggleWeightWatcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = Utilities.getPrefs(this);
        boolean show = sp.getBoolean(TestingUtils.SHOW_WEIGHT_WATCHER, true);

        show = !show;
        sp.edit().putBoolean(TestingUtils.SHOW_WEIGHT_WATCHER, show).apply();

        Launcher launcher = (Launcher) LauncherAppState.getInstance().getModel().getCallback();
        if (launcher != null && launcher.mWeightWatcher != null) {
            launcher.mWeightWatcher.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        finish();
    }
}
