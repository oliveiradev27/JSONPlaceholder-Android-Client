package br.dev.oliveira.jsonplaceholderclient.utils.infra;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public static Boolean hasInternet(Context context) {
        boolean conectado = false;
        ConnectivityManager conectivtyManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        }
        return conectado;
    }

}
