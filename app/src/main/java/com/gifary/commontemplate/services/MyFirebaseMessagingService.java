package com.gifary.commontemplate.services;

import android.media.RingtoneManager;
import android.net.Uri;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by gifary on 6/7/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG =MyFirebaseMessagingService.class.getSimpleName() ;
    final Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Notification notification =new Notification(getBaseContext());
        notification.showSimpleNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            /*Notification notification =new Notification(getBaseContext());
            notification.showSimpleNotification(data.get("title"),data.get("text"));*/
        }


    }
}
