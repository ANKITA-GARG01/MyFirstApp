package com.example.fakecallerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class fakecall extends BroadcastReceiver  {


    @Override
    public void onReceive(Context context, Intent intent) throws ClassCastException{

      // Extract the caller's details from the intent
    String a = intent.getStringExtra("name");
    String b = intent.getStringExtra("phoneNumber");

    // Show a toast message (replace this with the logic for showing the fake call screen)
        Toast.makeText(context, "Incoming call from: " + a + " (" + b+ ")", Toast.LENGTH_LONG).show();
new Handler().postDelayed(()->{
    // You can also start an activity for the fake call screen here:
    Intent fakeCallScreen = new Intent(context, callscreen.class);
    fakeCallScreen.putExtra("phoneNumber", b);
    fakeCallScreen.putExtra("name", a);
    fakeCallScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(fakeCallScreen);},5000);
}}




//call ringing
//recorded audio
// 