// **************************
// * Panagiotis Beligiannis *
// **************************
package com.example.homecare;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Activity5 extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private TextView xValue, yValue, zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_child);// Initialize TextViews
        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        zValue = findViewById(R.id.zValue);

        // Get SensorManager instance
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the accelerometer sensor
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            // Accelerometer is not available
            xValue.setText("Accelerometer not available");
            yValue.setText("");
            zValue.setText("");
        }
    }

    // Back Button Click - Return to MainActivity
    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clears other activities
        startActivity(intent);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // Check if the sensor type is accelerometer
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Get the x, y, and z values
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Update the TextViews
            xValue.setText("X: " + x);
            yValue.setText("Y: " + y);
            zValue.setText("Z: " + z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the listener when the activity is resumed
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener when the activity is paused
        if (accelerometerSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }
}