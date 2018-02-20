package br.dev.oliveira.jsonplaceholderclient.presenters;


import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.business.PostBusiness;
import br.dev.oliveira.jsonplaceholderclient.business.UserBusinnes;
import br.dev.oliveira.jsonplaceholderclient.contracts.Base;
import br.dev.oliveira.jsonplaceholderclient.contracts.FormPostContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnBindDataListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.models.User;
import br.dev.oliveira.jsonplaceholderclient.utils.infra.NetworkUtils;

public class FormPostPresenter implements Base.Presenter, FormPostContract.Presenter {

    private FormPostContract.View mView;
    private PostBusiness mPostBusinnes;
    private UserBusinnes mUserBusinnes;
    private List<User> mListUsers = new ArrayList<>();
    private Integer selectedUser = 0;
    private Post mPost;

    public FormPostPresenter(FormPostContract.View view) {
        this.mView = view;
        this.mUserBusinnes = new UserBusinnes(this);
        this.mPostBusinnes = new PostBusiness(this);
    }

    @Override
    public void getUsers() {
        if (NetworkUtils.hasInternet(this.mView.getContext())) {

            this.mView.showProgressBar();

            OnBindDataListener<List<User>> listener = new OnBindDataListener<List<User>>() {
                @Override
                public void onBind(List<User> data) {
                    mListUsers.clear();
                    mListUsers.addAll(data);
                    fillListUsername();
                    mView.hideProgressBar();
                }
            };

            mUserBusinnes.get(listener);
        } else {
            mView.showMessageDialog(R.string.ocorreu_um_erro, R.string.internet_indisponivel);
        }
    }

    @Override
    public void onBackPressed() {
        mView.onBackPressed();
    }

    @Override
    public void setUserIdByPosition(int position) {
        selectedUser = mListUsers.get(position).getId();
    }

    @Override
    public int getPositionByUserId(int userId) {

        for (int i = 0; i < mListUsers.size(); i++) {
            User user = mListUsers.get(i);
            if (user.getId() == userId) {
                return i;
            }
        }
        return 0;

    }


    @Override
    public void getPost() {

        Integer postId = mView.getPostId();
        if (postId != 0) {
            mView.showProgressBar();

            OnBindDataListener<Post> listener = new OnBindDataListener<Post>() {
                @Override
                public void onBind(Post data) {
                    selectedUser = data.getUserId();
                    mPost = data;
                    hideProgressBar();
                }
            };

            this.mPostBusinnes.get(postId, listener);

        }

    }

    @Override
    public void fillListUsername() {
        this.mView.getListUsernames().clear();

        if (selectedUser == 0) {
            this.mView.getListUsernames().add("");
        }

        for (User user : this.mListUsers) {
            this.mView.getListUsernames().add(user.getName());
        }

        this.mView.setAdapterUsers();

        if (selectedUser != 0) {
            this.mView.bindPostData(mPost);
        }
    }

    @Override
    public void showMessageDialog(int title, int message) {
        mView.showMessageDialog(title, message);
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
    public void save(final String title, String body) {

        if (!NetworkUtils.hasInternet(mView.getContext())) {
            this.mView.showMessageDialog(R.string.ocorreu_um_erro, R.string.internet_indisponivel);
            return;
        }


        if (title.isEmpty() || body.isEmpty() || selectedUser == 0) {

            if (title.isEmpty())
                this.mView.validateInputTitle();

            if (body.isEmpty())
                this.mView.validateInputBody();

            if (selectedUser == 0)
                this.mView.validateInputUserId();

        } else {

            Post post = new Post(mView.getPostId(), selectedUser, title, body);

            showProgressBar();

            OnBindDataListener<Boolean> listener = new OnBindDataListener<Boolean>() {
                @Override
                public void onBind(Boolean data) {
                    if (data) {
                        hideProgressBar();
                        showMessageDialog(R.string.mensagem_do_sistema, R.string.salvo_com_sucesso);
                    }
                }
            };

            this.mPostBusinnes.save(post, listener);
        }
    }

}
