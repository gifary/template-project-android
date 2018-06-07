package com.gifary.commontemplate.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.gifary.commontemplate.R;
import com.gifary.commontemplate.configuration.Constants;

/**
 * Created by gifary on 6/7/18.
 */

public class Notification {
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private Bitmap icon;
    private Context context;


    public Notification(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.context=context;

    }

    public  void showSimpleNotification(String title,String text){
        notificationBuilder = new NotificationCompat.Builder(context, Constants.PACKAGE_NAME)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                .setContentText(text)
        ;
        sendNotification();
    }


    private void sendNotification() {
        Intent notificationIntent = new Intent(context,context.getClass());
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(contentIntent);
        android.app.Notification notification = notificationBuilder.build();
        notification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= android.app.Notification.DEFAULT_SOUND;

        notificationManager.notify(1, notification);
    }

    public void clearAllNotifications() {
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }
}
