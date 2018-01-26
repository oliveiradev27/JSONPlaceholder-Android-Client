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
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class PostBusiness {

    private Context mContext;

    public PostBusiness(Context context) {
        this.mContext = context;
    }

    public void getPosts(final List<Post> posts) throws VolleyError {
        if (NetworkUtils.hasInternet(mContext)) {
            HttpRequest request = new HttpRequest(mContext);

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
                    new AlertDialog.Builder(mContext)
                            .setTitle(R.string.ocorreu_um_erro)
                            .setMessage(R.string.erro_posts_get)
                            .show();
                }
            };

            request.doGet(
                    NetworkConstants.ROOT + NetworkConstants.ENDPOINT.POSTS_GET,
                    listener
            );
        } else {
            new AlertDialog.Builder(mContext)
                    .setTitle(R.string.ocorreu_um_erro)
                    .setMessage(R.string.internet_indisponivel)
                    .show();
        }
    }
}
