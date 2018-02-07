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
import br.dev.oliveira.jsonplaceholderclient.listeners.OnBindDataListener;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.User;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class UserBusinnes {

    private Base.Presenter mPresenter;

    public UserBusinnes(Base.Presenter presenter){
        this.mPresenter = presenter;
    }


    public void get(Integer id, final OnBindDataListener<User> bindDataListener) {

        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {

                User user = new Gson().fromJson(result, User.class);
                bindDataListener.onBind(user);

            }

            @Override
            public void onError(VolleyError error) {
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_users_get);
            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.USER_GET + id, listener);

    }


    public void get(final OnBindDataListener<List<User>> onBindDataListener) {


        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {
                Type listType = new TypeToken<ArrayList<User>>() {
                }.getType();
                List<User> users = new Gson().fromJson(result, listType);
                onBindDataListener.onBind(users);
            }

            @Override
            public void onError(VolleyError error) {
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_users_get);
            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.USERS_GET, listener);

    }


}
