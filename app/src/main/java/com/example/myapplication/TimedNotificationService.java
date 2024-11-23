package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimedNotificationService extends Service {

    private static final String TAG = "TimedNotificationService";
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "1";


    private final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Service")
            .setContentText(Constantes.mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);


    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final FutureTask<?> futureTask = new FutureTask<Void>(() -> {
        Log.d("Service", Constantes.mensaje);
        showNotification();
        stopSelf();
    }, null);

    private void showNotification() {
        try {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        } catch (SecurityException ex) {
            Log.e(TAG, "Failed to show notification due to security restrictions", ex);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "Service created");
        Log.d("Build.VERSION.SDK_INT", Build.VERSION.SDK_INT + "");
        Log.d("Build.VERSION_CODES.O", Build.VERSION_CODES.O + "");
        executorService.schedule(futureTask, Constantes.timeout, TimeUnit.MINUTES);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service", "Service started");
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service", "Service destroyed");
        futureTask.cancel(true);
    }

}