package com.chutipon.reviewx.manager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Nicha Rojsrikul on 17/12/2560.
 */

public class ShakeManager implements SensorEventListener {
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener mListener;
    private long mShakeTimestamp;
    private int count;

    public void setOnShakeListener(OnShakeListener listener){
        this.mListener = listener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //blank
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(mListener != null){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX*gX + gY*gY + gZ*gZ);

            if(gForce > SHAKE_THRESHOLD_GRAVITY){
                final long now = System.currentTimeMillis();

                if(mShakeTimestamp + SHAKE_SLOP_TIME_MS > now){
                    return;
                }

                if(mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now){
                    count = 0;
                }

                mShakeTimestamp = now;
                count++;

                mListener.onShake(count);
            }
        }
    }
}
