package br.dev.oliveira.jsonplaceholderclient.business;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnBindDataListener;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Comment;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class CommentBusiness {

    public void getByPost(
            @NonNull Integer postId,
            @NonNull final OnBindDataListener<List<Comment>> onBindDataListener
    ) {

        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {

                Type listType = new TypeToken<ArrayList<Comment>>() {
                }.getType();
                List<Comment> comments = new Gson().fromJson(result, listType);
                onBindDataListener.onBind(comments);
            }

            @Override
            public void onError(VolleyError error) {

            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.COMMENTS_BY_POST_GET + postId, listener);

    }


}
