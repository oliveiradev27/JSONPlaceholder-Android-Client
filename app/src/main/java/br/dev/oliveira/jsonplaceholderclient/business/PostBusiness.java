package br.dev.oliveira.jsonplaceholderclient.business;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.FormPostContract;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class PostBusiness
        implements PostsContract.Model,
        FormPostContract.Model {

    private PostsContract.Presenter mPresenter;


    public PostBusiness(PostsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getPosts(final List<Post> posts) {

        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {

                Type listType = new TypeToken<ArrayList<Post>>() {
                }.getType();
                List<Post> list = new Gson().fromJson(result, listType);
                posts.clear();
                posts.addAll(list);
                mPresenter.hideProgressBar();
            }

            @Override
            public void onError(VolleyError error) {

                mPresenter.hideProgressBar();
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_posts_get);

            }
        };

        HttpRequest.doGet(NetworkConstants.ENDPOINT.POSTS_GET, listener);

    }

    @Override
    public void post(Integer postId) {

    }

    @Override
    public void add(final Post post) {

        OnResponseRequestListener listener = new OnResponseRequestListener() {
            @Override
            public void onSuccess(String result) {
                Post p = new Gson().fromJson(result, Post.class);
                post.setId(p.getId());
                mPresenter.hideProgressBar();
                mPresenter.showMessageDialog(
                        R.string.mensagem_do_sistema,
                        R.string.post_add_sucesso
                );

            }

            @Override
            public void onError(VolleyError error) {
                mPresenter.hideProgressBar();
                mPresenter.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_save_post);
            }
        };

        HttpRequest.doPost(
                NetworkConstants.ENDPOINT.POSTS_POST,
                new Gson().toJson(post),
                listener
        );

    }

    @Override
    public void del(Integer postId) {

    }

    @Override
    public void upd(Post post) {

    }
}
