package br.dev.oliveira.jsonplaceholderclient.presenters;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.business.PostBusiness;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;

public class PostPresenter implements PostsContract.Presenter {

    private PostsContract.View mView;
    private PostBusiness mModel;

    public PostPresenter(PostsContract.View view) {
        this.mView = view;
        this.mModel = new PostBusiness(this);
    }

    @Override
    public void getPosts(final List<Post> posts) {
        if (NetworkUtils.hasInternet(mView.getContext())) {

            mView.showProgressBar();
            this.mModel.getPosts(posts);

        } else {
            this.showMessageDialog(R.string.ocorreu_um_erro, R.string.internet_indisponivel);
        }
    }

    @Override
    public void goToPagePost(Integer id) {
        this.mView.goToPagePost(id);
    }

    @Override
    public void goToPostForm(Integer id) {
        this.mView.goToPostForm(id);
    }

    @Override
    public void showProgressBar() {
        this.mView.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        this.mView.hideProgressBar();
    }

    @Override
    public void showMessageDialog(int title, int message) {
        this.mView.showMessageDialog(title, message);
    }

}
