package com.example.devicesensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    List<Sensor> sensorList;
    StringBuilder sensorText;
    // Update the sensor list text view to display the list of sensors.
    TextView sensorTextView;
    private AppCompatButton listenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorTextView = findViewById(R.id.sensorListTV);
        listenBtn = findViewById(R.id.listnerBtn);
        sensorText = new StringBuilder();
        sensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList =
                sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensorList) {
            sensorText.append(sensor.getName()).append(System.getProperty("line.separator"));
        }


        sensorTextView.setText(sensorText);


        listenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SensorListner.class));
            }
        });
    }
}