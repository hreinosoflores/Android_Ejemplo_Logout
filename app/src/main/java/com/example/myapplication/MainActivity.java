package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main";

    private AlarmManager alarmManager;

    private PendingIntent pendingIntent;


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
//                Intent intent = new Intent(getBaseContext(), ClearSharedPreferencesService.class);
//                startService(intent);

            alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + Constantes.timeout * 60000,
                    pendingIntent);
            Log.e(TAG, "Alarma iniciada");
        });

        btnDetenerServicio.setOnClickListener(view -> {
//                Intent intent = new Intent(getBaseContext(), ClearSharedPreferencesService.class);
//                stopService(intent);

            alarmManager.cancel(pendingIntent);
            Log.e(TAG, "Alarma detenida");

        });

        btnForm1.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Form1Activity.class);
            startActivity(intent);
        });

        btnForm2.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Form2Activity.class);
            startActivity(intent);
        });

        this.alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);

        this.pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "Canal 1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}