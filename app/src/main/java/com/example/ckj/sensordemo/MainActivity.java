package com.example.ckj.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor mAccelerometer,mOrientation,mLighrt;
    private TextView tAccelerometer,tOrientation,tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLighrt = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        tAccelerometer = (TextView) findViewById(R.id.accelerometer);
        tOrientation = (TextView) findViewById(R.id.orientation);
        tLight   = (TextView) findViewById(R.id.light);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,mLighrt,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            tAccelerometer.setText("加速度："+x+","+y+","+z);
        }else if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            tOrientation.setText("方位："+x+","+y+","+z);
        }else if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            tLight.setText("光线："+x+","+y+","+z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
