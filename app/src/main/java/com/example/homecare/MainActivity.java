// **************************
// * Panagiotis Beligiannis *
// **************************
package com.example.homecare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Exit Application Button
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> finishAffinity()); // Closes the application

        // Image Buttons
        setupImageButton(R.id.button1, R.drawable.im1, "Time Management System", Activity1.class);
        setupImageButton(R.id.button2, R.drawable.im2, "Smart-home Management", Activity2.class);
        setupImageButton(R.id.button3, R.drawable.im3, "Medication Management", Activity1.class);
        setupImageButton(R.id.button4, R.drawable.im4, "Nutrition Management", Activity3.class);
        setupImageButton(R.id.button5, R.drawable.im5, "Health Monitoring", Activity2.class);
        setupImageButton(R.id.button6, R.drawable.im6, "Entertainment System", Activity4.class);
        setupImageButton(R.id.button7, R.drawable.im7, "Support and Accompaniment", Activity3.class);
        setupImageButton(R.id.button8, R.drawable.im8, "Emergency Situation", Activity5.class);
    }

    private void setupImageButton(int buttonId, int imageResId, String imageName, Class<?> activityClass) {
        ImageButton imageButton = findViewById(buttonId);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activityClass);
            // intent.putExtra("imageResId", imageResId);
            // intent.putExtra("imageName", imageName);
            startActivity(intent);
        });
    }
}
