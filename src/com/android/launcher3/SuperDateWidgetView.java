package com.android.launcher3;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.Locale;

public class SuperDateWidgetView
        extends LinearLayout
        implements TextWatcher
{
    private String bQ = "";
    private float bR;
    private SuperDoubleShadowTextClock bS;
    private SuperDoubleShadowTextClock bT;
    private int bU = 0;

    public SuperDateWidgetView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    private void bq()
    {
        this.bQ = "";
        br();
    }

    private void br()
    {
        if (this.bU <= 0) {
            return;
        }
        String str = this.bS.getText().toString();
        if (this.bQ.equals(str)) {
            return;
        }
        this.bQ = str;
        if (str.isEmpty()) {
            return;
        }
        TextPaint localTextPaint = this.bS.getPaint();
        float f2 = localTextPaint.getTextSize();
        float f1 = this.bR;
        int i = 0;
        float f3 = 0;
        if (i < 10)
        {
            //localTextPaint.setTextSize(f1);
            f3 = localTextPaint.measureText(str);
            if (f3 > this.bU) {}
        }
        else
        {
            if (Float.compare(f1, f2) == 0) {
                //localTextPaint.setTextSize(f2);
            }
        }

        f1 = f1 * this.bU / f3;
        i++;
        //this.bS.setTextSize(0, f1);
        bt();
    }

    private void bs(View paramView, int paramInt)
    {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)paramView.getLayoutParams();
        lp.setMarginEnd(paramInt);
        lp.resolveLayoutDirection(paramView.getLayoutDirection());
    }

    private void bt()
    {
        Object localObject = Locale.getDefault();
        if ((localObject != null) && (Locale.ENGLISH.getLanguage().equals(((Locale)localObject).getLanguage())))
        {
            TextPaint localTextPaint = this.bS.getPaint();
            localObject = new Rect();
            localTextPaint.getTextBounds("x", 0, 1, (Rect)localObject);
            int i = ((Rect)localObject).height();
            float f = localTextPaint.getFontMetrics().ascent;
            this.bT.setPadding(0, 0, 0, (int)(Math.abs(f) - i) / 2);
        }
    }

    public void afterTextChanged(Editable paramEditable)
    {
        br();
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        this.bS = ((SuperDoubleShadowTextClock)findViewById(R.id.date_text1));
        this.bR = this.bS.getTextSize();
        this.bS.addTextChangedListener(this);
        this.bS.ba(DateFormat.getBestDateTimePattern(Locale.getDefault(), "MMMMd"));
        this.bT = ((SuperDoubleShadowTextClock)findViewById(R.id.date_text2));
        this.bT.ba(getContext().getString(R.string.week_day_format, new Object[] { "EEEE", "yyyy" }));
        bt();
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
        DeviceProfile localDeviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        int k = View.MeasureSpec.getSize(paramInt1) / localDeviceProfile.inv.numColumns;
        int i = (k - localDeviceProfile.iconSizePx) / 2;
        int j = Math.max(1, (int)Math.ceil(getResources().getDimension(R.dimen.qsb_min_width_with_mic) / k));
        this.bU = ((localDeviceProfile.inv.numColumns - j) * k);
        bq();
        bs(this.bS, i);
        bs(this.bT, i);
        super.onMeasure(paramInt1, paramInt2);
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              C:\Users\Amir\Downloads\pixel-dex2jar.jar!\com\google\android\apps\nexuslauncher\qsb\DateWidgetView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */