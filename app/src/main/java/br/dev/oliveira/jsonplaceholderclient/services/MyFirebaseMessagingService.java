package br.dev.oliveira.jsonplaceholderclient.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.views.FormPostActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        this.showNotification(
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody()
        );

    }

    private void showNotification(String title, String bodyMessage) {

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                1,
                new Intent(this, FormPostActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this,
                "new_post"
        );

        Notification notification = builder.setContentText(bodyMessage)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher_transparent)
                .setContentIntent(pendingIntent)
                .setTicker(getString(R.string.notificacao_novo_post))
                .build();

        notification.flags = NotificationCompat.FLAG_AUTO_CANCEL;
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null)
            manager.notify(1, notification);

    }

}
