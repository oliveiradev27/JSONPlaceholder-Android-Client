package br.dev.oliveira.jsonplaceholderclient.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    private EventBus eventBus = EventBus.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("JSONPlaceholder", "action: " + intent.getAction());
        Log.d("JSONPlaceholder", "component: " + intent.getComponent());
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Boolean resultEvent = true;
            NetworkInfo info = (NetworkInfo) extras.get("networkInfo");
            if (info != null && !info.isConnectedOrConnecting()) {
                resultEvent = false;
            }
            eventBus.post(resultEvent);
        }
    }
}