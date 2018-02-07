package br.dev.oliveira.jsonplaceholderclient.business;

import android.support.annotation.NonNull;
import android.util.Log;

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
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class PostBusiness {

    private Base.Presenter mPresenter;


    public PostBusiness(Base.Presenter presenter) {
        this.mPresenter = presenter;
    }


    public void get(@NonNull Integer id, final OnBindDataListener<Post> bindDataListener) {

        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {

                Post p = new Gson().fromJson(result, Post.class);
                bindDataListener.onBind(p);

            }

            @Override
            public void onError(VolleyError error) {

                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_posts_get);

            }
        };


        HttpRequest.doGet(NetworkConstants.ENDPOINT.POST_GET + id, listener);

    }

    public void get(@NonNull  final OnBindDataListener<List<Post>> onBindDataListener) {

        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {

                Type listType = new TypeToken<ArrayList<Post>>() {
                }.getType();
                List<Post> list = new Gson().fromJson(result, listType);
                onBindDataListener.onBind(list);
            }

            @Override
            public void onError(VolleyError error) {

                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_posts_get);

            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.POSTS_GET, listener);

    }

    public void save(final Post post, final OnBindDataListener<Boolean> onBindDataListener) {

        final OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {
                //Post p = new Gson().fromJson(result, Post.class);
                //post.setId(p.getId());
                Log.d("JSONPlaceholder", result);
                onBindDataListener.onBind(true);

                mPresenter.showMessageDialog(
                        R.string.mensagem_do_sistema,
                        R.string.post_add_sucesso
                );

            }

            @Override
            public void onError(VolleyError error) {
                onBindDataListener.onBind(false);
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_save_post);
            }
        };

        if (post.getId() == 0) {
            Log.d("JSONPlaceholder", "POST");
            HttpRequest.doPost(
                    NetworkConstants.ENDPOINT.POST_POST,
                    new Gson().toJson(post),
                    listener
            );

        } else {
            Log.d("JSONPlaceholder", "PUT");
            HttpRequest.doPut(
                    NetworkConstants.ENDPOINT.POST_PUT + post.getId(),
                    new Gson().toJson(post),
                    listener
            );
        }

    }

    public void delete(Integer postId) {
        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {
                mPresenter.showMessageDialog(
                        R.string.mensagem_do_sistema,
                        R.string.excluido_com_sucesso
                );

            }

            @Override
            public void onError(VolleyError error) {
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_save_post);
            }
        };

        String url = NetworkConstants.ENDPOINT.POST_DELETE + postId;
        HttpRequest.doDelete(
                url,
                listener
        );
    }
}
