package br.dev.oliveira.jsonplaceholderclient.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("JSONPlaceholder", String.format(
                "%s : %s",
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody())
        );
    }
}
