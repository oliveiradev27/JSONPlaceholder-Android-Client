package br.dev.oliveira.jsonplaceholderclient.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostViewContract;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.presenters.PostViewPresenter;

public class PostViewActivity extends AppCompatActivity implements PostViewContract.View {

    private PostViewContract.Presenter mPresenter;
    private Post post;
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        this.mPresenter = new PostViewPresenter(this);
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

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void getPost() {
        this.mPresenter.getPost(this.post);
    }

    @Override
    public Context getContext() {
        return this;
    }

    private static class ViewHolder {

    }
}
