package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_main);
        Button btnComenzarServicio = findViewById(R.id.btnComenzarServicio);
        Button btnDetenerServicio = findViewById(R.id.btnDetenerServicio);
        Button btnForm1 = findViewById(R.id.btnForm1);
        Button btnForm2 = findViewById(R.id.btnForm2);

        btnComenzarServicio.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), TimedNotificationService.class);
            startService(intent);
        });

        btnDetenerServicio.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), TimedNotificationService.class);
            stopService(intent);
        });

        btnForm1.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Form1Activity.class);
            startActivity(intent);
        });

        btnForm2.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Form2Activity.class);
            startActivity(intent);
        });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "Canal 1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}