package br.dev.oliveira.jsonplaceholderclient.business;

import com.android.volley.VolleyError;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.constants.NetworkConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.network.HttpRequest;

public class PostBusiness implements PostsContract.Model {

    private PostsContract.Presenter mPresenter;


    public PostBusiness(PostsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getPosts(OnResponseRequestListener listener) {

        HttpRequest.doGet(NetworkConstants.ENDPOINT.POSTS_GET, listener);
    }

    @Override
    public void post(Integer postId) {

    }

    @Override
    public void add(Post post) {

    }

    @Override
    public void del(Integer postId) {

    }

    @Override
    public void upd(Post post) {

    }
}
