package br.dev.oliveira.jsonplaceholderclient.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.constants.PostConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostViewContract;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.presenters.PostViewPresenter;

public class PostContentFragment extends Fragment
        implements PostViewContract.View,
        View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PostViewContract.Presenter mPresenter;
    private Post mPost;
    private String mUsername;

    public static PostContentFragment newInstance(Integer postId) {

        Bundle args = new Bundle();
        PostContentFragment fragment = new PostContentFragment();

        args.putInt(PostConstants.ATTRIBUTES.ID, postId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();

        this.mPost = new Post();

        if (arguments != null) {
            this.mPost.setId(arguments.getInt(PostConstants.ATTRIBUTES.ID, 0));
        } else {
            this.mPost.setId(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.mPresenter = new PostViewPresenter(this);

        View rootView = inflater.inflate(
                R.layout.fragment_post_content,
                container,
                false
        );

        this.mViewHolder.mButtonSavePost = rootView.findViewById(R.id.button_save_post);
        this.mViewHolder.mLinearContentPost = rootView.findViewById(R.id.linear_content_post);
        this.mViewHolder.mTextBody = rootView.findViewById(R.id.text_post_body);
        this.mViewHolder.mTextUsername = rootView.findViewById(R.id.text_post_user);
        this.mViewHolder.mTextTitle = rootView.findViewById(R.id.text_post_title);
        this.mViewHolder.mProgressPostContent = rootView.findViewById(R.id.progress_post_content);
        this.mViewHolder.mViewTitleDivider = rootView.findViewById(R.id.view_post_title_divider);
        this.mViewHolder.mImageEmpty = rootView.findViewById(R.id.image_empty);

        setListeners();

        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPost();
    }

    @Override
    public void showMessageDialog(int title, int message) {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.mensagem_do_sistema)
                .setMessage(R.string.carregando)
                .show();
    }

    @Override
    public void showProgressBar() {

        this.mViewHolder.mLinearContentPost.setVisibility(View.GONE);
        this.mViewHolder.mButtonSavePost.setVisibility(View.GONE);
        this.mViewHolder.mImageEmpty.setVisibility(View.GONE);
        this.mViewHolder.mViewTitleDivider.setVisibility(View.GONE);


        this.mViewHolder.mProgressPostContent.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        this.mViewHolder.mProgressPostContent.setVisibility(View.GONE);
    }

    @Override
    public Integer getPostId() {
        return this.mPost.getId();
    }

    @Override
    public void setPostData() {

        this.mViewHolder.mLinearContentPost.setVisibility(View.VISIBLE);
        this.mViewHolder.mButtonSavePost.setVisibility(View.VISIBLE);
        this.mViewHolder.mViewTitleDivider.setVisibility(View.VISIBLE);
        this.mViewHolder.mImageEmpty.setVisibility(View.GONE);

        this.mViewHolder.mTextTitle.setText(this.mPost.getTitle());
        this.mViewHolder.mTextBody.setText(this.mPost.getBody());
    }

    @Override
    public void setUsername(String username) {
        this.mUsername = username;
        this.mViewHolder.mTextUsername.setText(username);
    }

    @Override
    public void getPost() {
        this.mPresenter.getPost(this.mPost);
    }

    @Override
    public void goToPostForm() {
        Intent intent = new Intent(getContext(), FormPostActivity.class);
        intent.putExtra(PostConstants.ATTRIBUTES.ID, this.getPostId());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_save_post) {
            this.mPresenter.goToPostForm();
        }
    }

    private void setListeners() {
        this.mViewHolder.mButtonSavePost.setOnClickListener(this);
    }

    private static class ViewHolder {
        LinearLayout mLinearContentPost;
        TextView mTextTitle, mTextBody, mTextUsername;
        ProgressBar mProgressPostContent;
        Button mButtonSavePost;
        View mViewTitleDivider;
        ImageView mImageEmpty;
    }
}
