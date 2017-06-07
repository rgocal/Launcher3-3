package com.android.launcher3a;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.android.launcher3a.compat.LauncherAppsCompat;
import com.android.launcher3a.compat.UserHandleCompat;

public abstract class SuperQsb
        extends FrameLayout
        implements View.OnClickListener
{
    private static String bB = "com.google.android.googlequicksearchbox.TEXT_ASSIST";
    private final ArgbEvaluator bD = new ArgbEvaluator();
    private ObjectAnimator bE;
    protected View bF;
    private float bG;
    protected View bH;
    private final Interpolator bI = new AccelerateDecelerateInterpolator();
    private ObjectAnimator bJ;
    protected boolean bL;
    private boolean bM;
    protected final Launcher bO;

    public SuperQsb(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.bO = Launcher.getLauncher(paramContext);
    }

    private void bb()
    {
        this.bM = true;
        if (this.bF != null)
        {
            this.bF.setAlpha(0.0F);
            if ((this.bJ != null) && (this.bJ.isRunning())) {
                this.bJ.end();
            }
        }
        if (this.bH != null)
        {
            if ((this.bE != null) && (this.bE.isRunning())) {
                this.bE.end();
            }
            this.bH.setAlpha(0.0F);
        }
    }

    private void bc(boolean paramBoolean)
    {
        if (this.bM)
        {
            this.bM = false;
            if (this.bF != null)
            {
                this.bF.setAlpha(1.0F);
                if (this.bJ != null)
                {
                    this.bJ.start();
                    if (!paramBoolean) {
                        this.bJ.end();
                    }
                }
            }
            if (this.bH != null)
            {
                this.bH.setAlpha(1.0F);
                if (this.bE != null)
                {
                    this.bE.start();
                    if (!paramBoolean) {
                        this.bE.end();
                    }
                }
            }
        }
    }

    protected void bh()
    {
        if (!this.bO.useVerticalBarLayout())
        {
            this.bH = this.bO.getLayoutInflater().inflate(R.layout.qsb_connector, this, false);
            addView(this.bH, 0);
            int j = getResources().getColor(R.color.qsb_connector);
            int i = getResources().getColor(R.color.qsb_background);
            ColorDrawable d = new ColorDrawable(j);
            this.bH.setBackground(d);
            this.bE = ObjectAnimator.ofObject(d, "color", this.bD, new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
            this.bE.setDuration(200L);
            this.bE.setInterpolator(this.bI);
        }
    }

    private void bl(String paramString)
    {
        try
        {
            Context localContext = getContext();
            Intent localIntent = new Intent();
            localIntent.setAction(paramString);
            localContext.startActivity(localIntent.setPackage("com.google.android.googlequicksearchbox")); //.addFlags(268468224)
        }
        catch (ActivityNotFoundException p)
        {
            LauncherAppsCompat.getInstance(getContext()).showAppDetailsForProfile(new ComponentName("com.google.android.googlequicksearchbox", ".SearchActivity"), UserHandleCompat.myUserHandle());
        }
    }

    protected abstract int be(boolean paramBoolean);

    public void bm()
    {
        this.bL = this.bO.useVerticalBarLayout();
        int bN = be(this.bL);
        if (this.bF != null) {
            removeView(this.bF);
        }
        this.bF = LayoutInflater.from(getContext()).inflate(bN, this, false);
        this.bG = getResources().getDimensionPixelSize(R.dimen.qsb_button_height);
        addView(this.bF);
        this.bJ = ObjectAnimator.ofFloat(this.bF, "elevation", new float[] { 0.0F, this.bG });
        this.bJ.setDuration(200L);
        this.bJ.setInterpolator(this.bI);
        if (this.bM) {
            bb();
        }
        this.bF.setOnClickListener(this);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
    }

    public void onClick(View paramView)
    {
        bl(bB);
    }

    public void onWindowFocusChanged(boolean paramBoolean)
    {
        super.onWindowFocusChanged(paramBoolean);
    }

    protected void onWindowVisibilityChanged(int paramInt)
    {
        super.onWindowVisibilityChanged(paramInt);
        bc(false);
    }
}


/* Location:              C:\Users\Amir\Downloads\pixel-dex2jar.jar!\com\google\android\apps\nexuslauncher\qsb\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */