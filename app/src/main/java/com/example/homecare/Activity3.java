// **************************
// * Panagiotis Beligiannis *
// **************************

package com.example.homecare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_child);
        String phoneNumber = "6912345678"; // Replace with the actual number
        String message = "ΧρειάζομαιΒοήθεια";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("viber://contact?number=" + phoneNumber));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // show text message
        Context context = getApplicationContext();
        CharSequence text = "Μήνυμα από 6912345678 : Χρειάζομαι Βοήθεια ";
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, text, duration).show();

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Context c = getApplicationContext();
            CharSequence t = " Δεν έχετε λογαριασμό στο Viber ";
            int d = Toast.LENGTH_LONG;
            Toast.makeText(c, t, d).show();

        }
    }

    // Back Button Click - Return to MainActivity
    public void goBack(View view) {
        finish();
    }
}