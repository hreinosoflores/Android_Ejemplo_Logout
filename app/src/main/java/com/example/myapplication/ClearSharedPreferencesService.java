package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClearSharedPreferencesService extends Service {

    private final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Service")
            .setContentText(Constantes.mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);



    /****DEPRECATED*/

    private final Handler handler = new Handler();

    private final Runnable runnable = () -> {
        Log.e("Service", Constantes.mensaje);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());
        notificationManager.notify(1, builder.build());
        stopSelf();
    };

    /****************************************************************************************************/

    private final Timer timer = new Timer();

    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Log.e("Service", Constantes.mensaje);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());
            notificationManager.notify(1, builder.build());
            stopSelf();
        }
    };

    /****************************************************************************************************/

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final FutureTask<?> futureTask = new FutureTask<Void>(() -> {
        Log.e("Service", Constantes.mensaje);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());
        notificationManager.notify(1, builder.build());
        stopSelf();
    }, null);

    /****************************************************************************************************/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "Service created");
        Log.e("Build.VERSION.SDK_INT", Build.VERSION.SDK_INT + "");
        Log.e("Build.VERSION_CODES.O", Build.VERSION_CODES.O + "");
        //handler.postDelayed(runnable, 10000);
        //timer.schedule(task,10000);
        executorService.schedule(futureTask, 10, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Service started");
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Service", "Service destroyed");
        //handler.removeCallbacks(runnable);
        //timer.cancel();
        futureTask.cancel(true);
    }


}