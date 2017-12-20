package com.chutipon.reviewx;

import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

/**
 * Created by Nicha Rojsrikul on 19/12/2560.
 */

public class MinMaxFilter implements InputFilter {
    private int mIntMin, mIntMax;
    private Activity activity;

    public MinMaxFilter(Activity activity, int minValue, int maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
        this.activity = activity;
    }

    public MinMaxFilter(Activity activity, String minValue, String maxValue) {
        this.mIntMin = Integer.parseInt(minValue);
        this.mIntMax = Integer.parseInt(maxValue);
        this.activity = activity;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(mIntMin, mIntMax, input))
                return null;
        } catch (NumberFormatException nfe) {
            Toast.makeText(activity, "Input invalid",Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
