package br.dev.oliveira.jsonplaceholderclient.presenters;


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.business.PostBusiness;
import br.dev.oliveira.jsonplaceholderclient.business.UserBusinnes;
import br.dev.oliveira.jsonplaceholderclient.contracts.FormPostContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.User;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;

public class FormPostPresenter implements FormPostContract.Presenter {

    private FormPostContract.View mView;
    private PostBusiness mPostBusinnes;
    private UserBusinnes mUserBusinnes;

    public FormPostPresenter(FormPostContract.View view) {
        this.mView = view;
        this.mUserBusinnes = new UserBusinnes();
    }

    @Override
    public void getUsers(final List<User> users) {

        if (NetworkUtils.hasInternet(this.mView.getContext())) {

            OnResponseRequestListener listener = new OnResponseRequestListener() {
                @Override
                public void onSuccess(String result) {
                    Type listType = new TypeToken<ArrayList<User>>() {
                    }.getType();
                    List<User> list = new Gson().fromJson(result, listType);
                    users.addAll(list);
                }

                @Override
                public void onError(VolleyError error) {
                    mView.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_users_get);
                }
            };

            mUserBusinnes.getUsers(listener);

        } else {
            mView.showMessageDialog(R.string.ocorreu_um_erro, R.string.internet_indisponivel);
        }
    }
}
