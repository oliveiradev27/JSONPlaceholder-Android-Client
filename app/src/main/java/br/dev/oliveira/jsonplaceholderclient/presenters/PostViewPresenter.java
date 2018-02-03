package br.dev.oliveira.jsonplaceholderclient.presenters;

import br.dev.oliveira.jsonplaceholderclient.business.PostBusiness;
import br.dev.oliveira.jsonplaceholderclient.contracts.Base;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostViewContract;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;

public class PostViewPresenter implements
        Base.Presenter,
        PostViewContract.Presenter {

    private PostViewContract.View mView;
    private PostBusiness mPostBusiness;

    public PostViewPresenter (PostViewContract.View view) {
        this.mView = view;
        this.mPostBusiness = new PostBusiness(this);
    }


    @Override
    public void showMessageDialog(int title, int message) {

    }

    @Override
    public void showConfirmAction(int message) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void getPost(Post post) {
        if (NetworkUtils.hasInternet(mView.getContext())) {
            this.mPostBusiness.get(post);
        }
    }
}
