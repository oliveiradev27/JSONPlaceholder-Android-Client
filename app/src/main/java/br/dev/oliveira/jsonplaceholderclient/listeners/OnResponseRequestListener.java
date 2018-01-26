package br.dev.oliveira.jsonplaceholderclient.listeners;


import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public interface OnResponseRequestListener {

    void onSuccess(String result);
    void onError(VolleyError error);
}
