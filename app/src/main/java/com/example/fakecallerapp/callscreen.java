package com.example.fakecallerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fakecallerapp.R;

public class callscreen extends AppCompatActivity {

    private TextView numberTextView;
    private ImageButton acceptButton, declineButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Edge-to-edge display to ensure full screen
        setContentView(R.layout.activity_callscreen);

        // Initialize views
        TextView callerTextView = findViewById(R.id.caller);
        numberTextView = findViewById(R.id.number);
        acceptButton = findViewById(R.id.accept);
        declineButton = findViewById(R.id.decline);

        // Set up media player with the ringtone
        setupRingtone();

        // Retrieve caller name and phone number from intent
        Intent intent = getIntent();
        String callerName = intent.getStringExtra("name");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        // Set caller info on UI
        callerTextView.setText(callerName);
        numberTextView.setText(phoneNumber);

        // Apply edge-to-edge system bar padding to prevent layout overlap
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up button click listeners
        setupButtonListeners();
    }

    private void setupRingtone() {
        // Initialize MediaPlayer and start the ringtone
        mediaPlayer = MediaPlayer.create(this, R.raw.ringtone);
        mediaPlayer.setLooping(true); // Keep looping until call is accepted or declined
        mediaPlayer.start();
    }

    private void setupButtonListeners() {
        // Accept button click listener
        acceptButton.setOnClickListener(v -> {
            stopRingtone();
            Toast.makeText(callscreen.this, "Call Accepted", Toast.LENGTH_SHORT).show();
            // Logic to accept the call or transition to another activity
            // e.g., startActivity(new Intent(CallScreen.this, InCallActivity.class));
        });

        // Decline button click listener
        declineButton.setOnClickListener(v -> {
            stopRingtone();
            Toast.makeText(callscreen.this, "Call Declined", Toast.LENGTH_SHORT).show();
            // Logic to decline the call or close the activity
            // finish();  // Close the activity if needed
        });
    }

    private void stopRingtone() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRingtone();  // Stop ringtone when the activity is paused
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRingtone();  // Ensure media player is released when activity is destroyed
    }
}
