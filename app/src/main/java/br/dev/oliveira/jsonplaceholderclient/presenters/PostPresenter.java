package br.dev.oliveira.jsonplaceholderclient.presenters;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class PostPresenter implements PostsContract.Presenter {

    private PostsContract.View mView;

    public PostPresenter(PostsContract.View view) {
        this.mView = view;
    }

    @Override
    public void getPosts(final List<Post> posts) {
        if (NetworkUtils.hasInternet(mView.getContext())) {
            HttpRequest request = new HttpRequest(mView.getContext());

            OnResponseRequestListener listener = new OnResponseRequestListener() {
                @Override
                public void onSuccess(String result) {
                    Type listType = new TypeToken<ArrayList<Post>>() {
                    }.getType();
                    List<Post> list = new Gson().fromJson(result, listType);
                    posts.addAll(list);
                }

                @Override
                public void onError(VolleyError error) {
                    mView.showDialog(R.string.ocorreu_um_erro, R.string.erro_posts_get);
                }
            };

            request.doGet(
                    NetworkConstants.ROOT + NetworkConstants.ENDPOINT.POSTS_GET,
                    listener
            );
        } else {
            this.mView.showDialog(R.string.ocorreu_um_erro, R.string.internet_indisponivel);
        }
    }

    @Override
    public void add() {
        this.mView.add();
    }

}
