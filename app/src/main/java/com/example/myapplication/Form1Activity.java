package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Form1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //SI HAY UN TIMER EN PROCESO, DETENER ESE TIMER
        Intent intent = new Intent(this, TimedNotificationService.class);
        stopService(intent);

    }


    @Override
    protected void onPause() {
        super.onPause();
        //REINICIA EL TIMER AQUI
        Intent intent = new Intent(this, TimedNotificationService.class);
        startService(intent);


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}