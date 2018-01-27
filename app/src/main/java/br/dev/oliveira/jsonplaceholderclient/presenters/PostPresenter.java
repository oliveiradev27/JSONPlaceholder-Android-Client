package br.dev.oliveira.jsonplaceholderclient.presenters;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.business.PostBusiness;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;

public class PostPresenter implements PostsContract.Presenter {

    private PostsContract.View mView;
    private PostsContract.Model mModel;

    public PostPresenter(PostsContract.View view) {
        this.mView = view;
        this.mModel = new PostBusiness(this);
    }

    @Override
    public void getPosts(final List<Post> posts) {
        if (NetworkUtils.hasInternet(mView.getContext())) {

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
                    mView.showMessageDialog(R.string.ocorreu_um_erro, R.string.erro_posts_get);
                }
            };

            this.mModel.getPosts(listener);

        } else {
            this.mView.showMessageDialog(R.string.ocorreu_um_erro, R.string.internet_indisponivel);
        }
    }

    @Override
    public void goToPagePost() {
        this.mView.goToPagePost();
    }

}
