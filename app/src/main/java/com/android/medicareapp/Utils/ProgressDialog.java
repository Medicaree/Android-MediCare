package com.android.medicareapp.Utils;

import android.content.Context;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.WanderingCubes;

public class ProgressDialog extends SpinKitView {
    SpinKitView spinKitView;
    WanderingCubes dots;
    public ProgressDialog(Context context, SpinKitView spinKitView) {
        super(context);
        this.spinKitView = spinKitView;
        dots = new WanderingCubes();
    }
    public void startProgress() {
        spinKitView.setVisibility(VISIBLE);
        setIndeterminateDrawable(dots);
    }
    public void stopProgress() {
        spinKitView.setVisibility(GONE);
        unscheduleDrawable(dots);
    }
}
