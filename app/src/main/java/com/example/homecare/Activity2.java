// **************************
// * Panagiotis Beligiannis *
// **************************
package com.example.homecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Activity2 extends AppCompatActivity {
    private TextView dataTextView;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private static final String TAG = "Activity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_child);

        dataTextView = findViewById(R.id.dataTextView);

        fetchIoTData("http://192.168.1.4/data");
    }

    public void goBack(View view) {
        finish(); // Close the current activity
    }

    private void fetchIoTData(String urlString) {
        executor.execute(() -> {
            String result = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000); // Set connection timeout to 5 seconds
                urlConnection.setReadTimeout(5000); // Set read timeout to 5 seconds

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    result = in.readLine();
                    in.close();

                    // Show success Toast on the main thread
                    final String finalResult = result;
                    handler.post(() -> {
                        Toast.makeText(getApplicationContext(), "Επιτυχής σύνδεση με συσκευή IoT : " + urlString, Toast.LENGTH_LONG).show();
                        dataTextView.setText("Δεδομένα: " + finalResult);
                    });
                } else {
                    result = "Σφάλμα σύνδεσης: HTTP " + responseCode;
                    Log.e(TAG, "HTTP Error: " + responseCode);
                    final String finalResult = result;
                    handler.post(() -> {
                        Toast.makeText(getApplicationContext(), "Σφάλμα σύνδεσης με συσκευή IoT : " + urlString + " HTTP " + responseCode, Toast.LENGTH_LONG).show();
                        dataTextView.setText(finalResult);
                    });
                }
                urlConnection.disconnect();
            } catch (IOException e) {
                result = "Σφάλμα σύνδεσης: " + e.getMessage();
                Log.e(TAG, "Connection Error: " + e.getMessage());
                final String finalResult = result;
                handler.post(() -> {
                    Toast.makeText(getApplicationContext(), "Σφάλμα σύνδεσης με συσκευή IoT : " + urlString + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                    dataTextView.setText(finalResult);
                });
            }

            // After 5 seconds, return to MainActivity
            handler.postDelayed(() -> {
                Intent backIntent = new Intent(Activity2.this, MainActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);
                finish();
            }, 5000);});
    }
}