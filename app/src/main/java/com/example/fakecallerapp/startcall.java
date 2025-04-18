package com.example.fakecallerapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class startcall extends AppCompatActivity {
  EditText name,phone ;
  AppCompatButton start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_startcall);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            name=findViewById(R.id.callerName);
            phone=findViewById(R.id.callerNumber);
            start=findViewById(R.id.startFakeCall);


                start.setOnClickListener(v1 -> {
                    String n = name.getText().toString();
                    String p = phone.getText().toString();

                    if ((!n.isBlank()) && (!p.isBlank())) {
                        schedulecall(n, p);
                    }
                    else {
                        Toast.makeText(this, "Please enter both name and phone number.", Toast.LENGTH_SHORT).show();

                    }
                });

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void schedulecall(String name,String phone)throws SecurityException{
        // Set a time for the fake call (e.g., 5 seconds from now)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        // Create an intent for the fake call
        Intent intent = new Intent(this, fakecall.class);
        intent.putExtra("phoneNumber", phone);
        intent.putExtra("name", name);


        // Create a PendingIntent to trigger the receiver
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Schedule the fake call using AlarmManager
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, " CALM DOWN WE GOT YOU !! call scheduled!", Toast.LENGTH_SHORT).show();


    }

}
//val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//if (alarmManager.canScheduleExactAlarms()) {
//    // App has permission to schedule exact alarms
//    Log.d("AlarmCheck", "Exact alarms can be scheduled.")
//} else {
//    // App does not have permission; direct the user to settings
//    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
//    startActivity(intent)
//}