// **************************
// * Panagiotis Beligiannis *
// **************************

package com.example.homecare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button showDateTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_child);

        // Get references to the UI elements
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        showDateTimeButton = findViewById(R.id.showDateTimeButton);

        // Set a click listener for the button
        showDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected date
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1; // Month is 0-based
                int day = datePicker.getDayOfMonth();

                // Get the selected time
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                // Show a Toast with the selected date and time
                String dateTimeString = day + "/" + month + "/" + year + " " + hour + ":" + minute;
                Toast.makeText(Activity1.this, "Selected Date and Time: " + dateTimeString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Back Button Click - Return to MainActivity
    public void goBack(View view) {
        finish();
    }
}