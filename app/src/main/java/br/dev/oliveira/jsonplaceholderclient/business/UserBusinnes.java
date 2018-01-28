package br.dev.oliveira.jsonplaceholderclient.business;

import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class UserBusinnes {

    public void getUsers (OnResponseRequestListener listener) {

        HttpRequest.doGet(NetworkConstants.ENDPOINT.USERS_GET, listener);

    }
}
