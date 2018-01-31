package br.dev.oliveira.jsonplaceholderclient.business;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.Base;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.User;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class UserBusinnes {

    private Base.Presenter mPresenter;

    public UserBusinnes(Base.Presenter presenter){
        this.mPresenter = presenter;
    }

    public void getUsers (final List<User> users) {


        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {
                Type listType = new TypeToken<ArrayList<User>>() {
                }.getType();
                List<User> list = new Gson().fromJson(result, listType);
                users.clear();
                users.addAll(list);
                users.get(0);
            }

            @Override
            public void onError(VolleyError error) {
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_users_get);
            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.USERS_GET, listener);

    }

    public void getUsernames (final List<String> usernames) {


        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {
                Type listType = new TypeToken<ArrayList<User>>() {
                }.getType();
                List<User> list = new Gson().fromJson(result, listType);
                usernames.clear();

                for (User user : list) {
                    usernames.add(user.getName());
                }
            }

            @Override
            public void onError(VolleyError error) {
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_users_get);
            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.USERS_GET, listener);

    }

}
