package br.dev.oliveira.jsonplaceholderclient.presenters;

import android.util.Log;

import br.dev.oliveira.jsonplaceholderclient.business.PostBusiness;
import br.dev.oliveira.jsonplaceholderclient.business.UserBusinnes;
import br.dev.oliveira.jsonplaceholderclient.contracts.Base;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostViewContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnBindDataListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.models.User;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;

public class PostViewPresenter implements
        Base.Presenter,
        PostViewContract.Presenter {

    private PostViewContract.View mView;
    private PostBusiness mPostBusiness;
    private UserBusinnes mUserBusiness;

    public PostViewPresenter(PostViewContract.View view) {
        this.mView = view;
        this.mPostBusiness = new PostBusiness(this);
        this.mUserBusiness = new UserBusinnes(this);
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
    public void getPost(final Post post) {

        if (post.getId() == 0)
            return;

        Log.i("JSONPlaceholder", post.getId().toString());

        if (NetworkUtils.hasInternet(mView.getContext())) {
            this.mView.showProgressBar();

            OnBindDataListener<Post> listener = new OnBindDataListener<Post>() {
                @Override
                public void onBind(Post data) {

                    post.setId(data.getId());
                    post.setTitle(data.getTitle());
                    post.setBody(data.getBody());
                    post.setUserId(data.getUserId());

                    PostViewPresenter.this.mView.setPostData();
                    PostViewPresenter.this.getUser(post.getUserId());
                    PostViewPresenter.this.mView.hideProgressBar();
                }
            };

            this.mPostBusiness.get(post.getId(), listener);
            // this.getUser(post.getUserId());
        }

    }

    @Override
    public void goToPostForm() {
        this.mView.goToPostForm();
    }

    private void getUser(Integer userId) {
        OnBindDataListener<User> listener = new OnBindDataListener<User>() {
            @Override
            public void onBind(User data) {
                PostViewPresenter.this.mView.setUsername(data.getName());
            }

        };

        this.mUserBusiness.get(userId, listener);
    }

    public void setData() {
        this.mView.setPostData();
    }
}
