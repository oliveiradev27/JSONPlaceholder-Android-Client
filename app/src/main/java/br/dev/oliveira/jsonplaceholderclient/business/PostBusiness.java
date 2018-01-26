package br.dev.oliveira.jsonplaceholderclient.business;

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

public class PostBusiness {

    private PostsContract.Presenter mPresenter;


    public PostBusiness(PostsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void getPosts(final List<Post> posts) throws VolleyError {

    }
}
