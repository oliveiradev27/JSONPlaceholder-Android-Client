package br.dev.oliveira.jsonplaceholderclient.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.dev.oliveira.jsonplaceholderclient.R;

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RECEIVER", "action: " + intent.getAction());
        Log.d("RECEIVER", "component: " + intent.getComponent());
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Log.d("RECEIVER", "key [" + key + "]: " + extras.get(key));

                if (key.equals("noConnectivity")) {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.ocorreu_um_erro)
                            .setMessage(R.string.internet_indisponivel)
                            .show();
                }
            }
        }
    }
}