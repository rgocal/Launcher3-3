package com.android.launcher3;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.launcher3.util.TransformingTouchDelegate;

public class SuperGContainerView extends SuperQsb
{
    private static final Rect sTempRect = new Rect();
    private final TransformingTouchDelegate bY;

    public SuperGContainerView(Context paramContext)
    {
        this(paramContext, null);
    }

    public SuperGContainerView(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public SuperGContainerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);

        bm();
        bh();
        if (this.bO.useVerticalBarLayout())
        {
            View.inflate(paramContext, R.layout.qsb_blocker_view, this);
            this.bY = null;
        }
        else {
            this.bY = new TransformingTouchDelegate(this);
        }

        SuperDateWidgetView v = (SuperDateWidgetView) LayoutInflater.from(getContext()).inflate(R.layout.date_widget, this, false);
        this.addView(v);
    }

    protected int be(boolean paramBoolean)
    {
        float f;
        if (this.bY != null)
        {
            TransformingTouchDelegate localTransformingTouchDelegate = this.bY;
            if (paramBoolean)
            {
                f = 0.0F;
                localTransformingTouchDelegate.extendTouchBounds(f);
            }
        }

        return R.layout.qsb_without_mic;
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
        if (this.bY != null) {
            //return false;
        }
        return super.dispatchTouchEvent(paramMotionEvent);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (this.bY != null) {
            this.bO.getWorkspace().findViewById(R.id.workspace_blocked_row).setTouchDelegate(this.bY);
        }
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.bY != null)
        {
            paramInt1 = 0;
            if (Utilities.isRtl(getResources())) {
                paramInt1 = this.bF.getLeft() - this.bO.getDeviceProfile().getWorkspacePadding(sTempRect).left;
            }
            this.bY.setBounds(paramInt1, this.bF.getTop(), this.bF.getWidth() + paramInt1, this.bF.getBottom());
        }
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
        int j = -getResources().getDimensionPixelSize(R.dimen.qsb_overlap_margin);
        DeviceProfile localObject1 = this.bO.getDeviceProfile();
        Rect localObject2 = localObject1.getWorkspacePadding(sTempRect);
        int i = View.MeasureSpec.getSize(paramInt1) - j;
        int k;

        int m = i - localObject2.left - localObject2.right;
        i = DeviceProfile.calculateCellWidth(m, localObject1.inv.numColumns) * localObject1.inv.numColumns;
        k = localObject2.left;
        m = (m - i) / 2;
        j += k + m;

        if (bO.useVerticalBarLayout()) {
            j = 0;
        }

        if (localObject1.isVerticalBarLayout())
        {
            k = getResources().getDimensionPixelSize(R.dimen.qsb_button_elevation);
            j += k;
        }

        if (this.bF != null)
        {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)this.bF.getLayoutParams();
            lp.width = (i / localObject1.inv.numColumns);
            if (this.bL) {
                lp.width = Math.max(lp.width, getResources().getDimensionPixelSize(R.dimen.qsb_min_width_with_mic));
            }
            lp.setMarginStart(j);
            lp.resolveLayoutDirection(lp.getLayoutDirection());
        }
        if (this.bH != null)
        {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)this.bH.getLayoutParams();
            lp.width = (j + lp.height / 2);
        }
        super.onMeasure(paramInt1, paramInt2);
    }

    public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.setPadding(0, 0, 0, 0);
    }
}


/* Location:              C:\Users\Amir\Downloads\pixel-dex2jar.jar!\com\google\android\apps\nexuslauncher\qsb\SuperGContainerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */