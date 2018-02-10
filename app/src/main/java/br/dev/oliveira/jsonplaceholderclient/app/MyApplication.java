package br.dev.oliveira.jsonplaceholderclient.app;

import android.app.Application;

import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequest.initialize(getApplicationContext());

    }
}
