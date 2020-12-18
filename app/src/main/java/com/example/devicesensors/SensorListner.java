package com.example.devicesensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorListner extends AppCompatActivity implements SensorEventListener {
    // System sensor manager instance.
    private SensorManager sensorManager;

    // Proximity and light sensors, as retrieved from the sensor manager.
    private Sensor sensorProximity;
    private Sensor sensorLight;
    private Sensor sensorHumidity;

    // TextViews to display current sensor values.
    private TextView textSensorLight, textSensorProximity, textSensorHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_listner);

        textSensorLight = findViewById(R.id.label_light);
        textSensorProximity = findViewById(R.id.label_proximity);
        textSensorHumidity = findViewById(R.id.label_humidity);

        sensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);


        sensorProximity = sensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        // Get the error message from string resources.
        String sensor_error = "no sensor";

        // If either mSensorLight or mSensorProximity are null, those sensors
        // are not available in the device.  Set the text to the error message
        if (sensorLight == null) {
            textSensorLight.setText(sensor_error);
        }
        if (sensorProximity == null) {
            textSensorProximity.setText(sensor_error);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onPause().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL)
        if (sensorProximity != null) {
            sensorManager.registerListener(this, sensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorLight != null) {
            sensorManager.registerListener(this, sensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorHumidity != null) {
            sensorManager.registerListener(this, sensorHumidity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is paused.
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
// The sensor type (as defined in the Sensor class).
        int sensorType = sensorEvent.sensor.getType();

        // The new data value of the sensor.  Both the light and proximity
        // sensors report one value at a time, which is always the first
        // element in the values array.
        float currentValue = sensorEvent.values[0];

        switch (sensorType) {
            // Event came from the light sensor.
            case Sensor.TYPE_LIGHT:
                // Set the light sensor text view to the light sensor string
                // from the resources, with the placeholder filled in.
                textSensorLight.setText(String.valueOf(currentValue));
                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                break;
            case Sensor.TYPE_PROXIMITY:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                textSensorProximity.setText(String.valueOf(currentValue));
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                textSensorProximity.setText(String.valueOf(currentValue));
                getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
                break;
            default:
                // do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}