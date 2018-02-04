package br.dev.oliveira.jsonplaceholderclient.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.constants.PostConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostViewContract;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.presenters.PostViewPresenter;

public class PostViewActivity extends AppCompatActivity implements PostViewContract.View {

    private PostViewContract.Presenter mPresenter;
    private Post post;
    private ViewHolder mViewHolder = new ViewHolder();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        this.post = new Post();
        this.mPresenter = new PostViewPresenter(this);


        this.mViewHolder.textPostTitle = findViewById(R.id.text_post_title);
        this.mViewHolder.textPostBody  = findViewById(R.id.text_post_body);
        this.mViewHolder.textPostUser  = findViewById(R.id.text_post_user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getPost();
    }

    @Override
    public void showMessageDialog(int title, int message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    @Override
    public void showConfirmAction(int message) {

    }

    @Override
    public void showProgressBar() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new ProgressDialog(this);
            dialog.setTitle(R.string.mensagem_do_sistema);
            dialog.setMessage(getString(R.string.carregando));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }
    }

    @Override
    public void hideProgressBar() {
        dialog.dismiss();
    }

    @Override
    public void getPost() {
        this.post.setId(this.getPostId());
        this.mPresenter.getPost(this.post);
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void setPostData() {
        this.mViewHolder.textPostTitle.setText(post.getTitle());
        this.mViewHolder.textPostBody.setText(post.getBody());
    }

    @Override
    public void setUsername(String username) {
        this.mViewHolder.textPostUser.setText(username);

    }

    public Integer getPostId() {
        return getIntent().getIntExtra(PostConstants.ATTRIBUTES.ID, 0);
    }

    private static class ViewHolder {
        TextView textPostTitle, textPostBody, textPostUser;
    }
}
