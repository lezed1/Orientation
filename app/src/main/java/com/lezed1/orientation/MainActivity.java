package com.lezed1.orientation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private OpenGLRenderer renderer;
    private Sensor senMagneticField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        GLSurfaceView view = new GLSurfaceView(this);
        renderer = new OpenGLRenderer();
        view.setRenderer(renderer);
        setContentView(view);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senMagneticField = senSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        senSensorManager.registerListener(renderer, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        senSensorManager.registerListener(renderer, senMagneticField, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(renderer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(renderer, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        senSensorManager.registerListener(renderer, senMagneticField, SensorManager.SENSOR_DELAY_FASTEST);
    }
}
