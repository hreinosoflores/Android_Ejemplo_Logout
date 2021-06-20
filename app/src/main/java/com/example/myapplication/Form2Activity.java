package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Form2Activity extends AppCompatActivity {

    private final String TAG = "Form2";

    private AlarmManager alarmManager;

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        this.alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        this.pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        //SI HAY UN TIMER EN PROCESO, DETENER ESE TIMER
//        Intent intent = new Intent(this, ClearSharedPreferencesService.class);
//        stopService(intent);

        alarmManager.cancel(pendingIntent);
        Log.e(TAG, "Alarma detenida");
    }


    @Override
    protected void onPause() {
        super.onPause();
//        //REINICIA EL TIMER AQUI
//        Intent intent = new Intent(this, ClearSharedPreferencesService.class);
//        startService(intent);

        alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + Constantes.timeout * 60000,
                pendingIntent);
        Log.e(TAG, "Alarma iniciada");
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