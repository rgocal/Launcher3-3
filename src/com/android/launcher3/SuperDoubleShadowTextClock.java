package com.android.launcher3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextClock;

public class SuperDoubleShadowTextClock
        extends TextClock
{
    private final float bA;
    private final float bw;
    private final int bx;
    private final float by;
    private final int bz;

    public SuperDoubleShadowTextClock(Context paramContext)
    {
        this(paramContext, null);
    }

    public SuperDoubleShadowTextClock(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public SuperDoubleShadowTextClock(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        TypedArray ta = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { R.attr.ambientShadowColor, R.attr.keyShadowColor, R.attr.ambientShadowBlur, R.attr.keyShadowBlur, R.attr.keyShadowOffset }, paramInt, 0);
        this.bw = ta.getDimension(2, 0.0F);
        this.by = ta.getDimension(3, 0.0F);
        this.bA = ta.getDimension(4, 0.0F);
        this.bx = ta.getColor(0, 0);
        this.bz = ta.getColor(1, 0);
        ta.recycle();
        setShadowLayer(Math.max(this.by + this.bA, this.bw), 0.0F, 0.0F, this.bz);
    }

    public void ba(CharSequence paramCharSequence)
    {
        setFormat24Hour(paramCharSequence);
        setFormat12Hour(paramCharSequence);
    }

    protected void onDraw(Canvas paramCanvas)
    {
        getPaint().setShadowLayer(this.by, 0.0F, this.bA, this.bz);
        super.onDraw(paramCanvas);
        getPaint().setShadowLayer(this.bw, 0.0F, 0.0F, this.bx);
        super.onDraw(paramCanvas);
    }
}


/* Location:              C:\Users\Amir\Downloads\pixel-dex2jar.jar!\com\google\android\apps\nexuslauncher\qsb\DoubleShadowTextClock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */