package com.example.submission2.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.submission2.MainActivity;
import com.example.submission2.R;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("aSwitch", "aSwitch");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "102")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_baseline_notifications_24))
                .setContentTitle(context.getResources().getString(R.string.github_users))
                .setContentText(context.getResources().getString(R.string.text_notif))
                .setSubText(context.getResources().getString(R.string.github_users))
                .setAutoCancel(true);

        Notification notification = builder.build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("102", "AlarmManager Channel", NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId("102");

            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if (notificationManager != null){
            notificationManager.notify(2, notification);
        }

    }
}
