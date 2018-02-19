package br.dev.oliveira.jsonplaceholderclient.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.adapters.PostsListAdapter;
import br.dev.oliveira.jsonplaceholderclient.constants.PostConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnListClickInteractionListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.presenters.PostsPresenter;
import br.dev.oliveira.jsonplaceholderclient.viewholders.PostsViewHolder;


public class ListPostFragment extends Fragment implements PostsContract.View {

    private ArrayList<Post> mPosts = new ArrayList<>();
    private ViewHolder mViewHolder = new ViewHolder();
    private RecyclerView.Adapter<PostsViewHolder> mAdapter;
    private Integer seletedPost;

    private PostsContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mPresenter = new PostsPresenter(this);
        View rootView = inflater.inflate(R.layout.fragment_list_post, container, true);


        this.mViewHolder.mTextTitlePosts = rootView.findViewById(R.id.text_title_posts);
        this.mViewHolder.mProgressPosts = rootView.findViewById(R.id.progress_posts);
        this.mViewHolder.mRecyclerPosts = rootView.findViewById(R.id.recycler_posts);

        // Setando o layout manager do recyclerview
        this.mViewHolder.mRecyclerPosts.setLayoutManager(
                new LinearLayoutManager(
                        rootView.getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        OnListClickInteractionListener listener = new OnListClickInteractionListener() {
            @Override
            public void onClickShow(Integer postId) {
                ListPostFragment.this.seletedPost = postId;
                ListPostFragment.this.mPresenter.goToPagePost(postId);
            }

            @Override

            public void onClickDelete(Integer postId) {
                ListPostFragment.this.mPresenter.showConfirmAction(postId);
            }
        };

        mAdapter = new PostsListAdapter(getContext(), this.mPosts, listener);
        mAdapter.notifyDataSetChanged();

        this.mViewHolder.mRecyclerPosts.setAdapter(mAdapter);

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPosts();
    }

    @Override
    public void showMessageDialog(int title, int message) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    @Override
    public void showConfirmAction(int message) {
    }

    @Override
    public void showProgressBar() {
        this.mViewHolder.mProgressPosts.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        this.mViewHolder.mProgressPosts.setVisibility(View.GONE);
    }

    @Override
    public void getPosts() {
        this.mPresenter.getPosts(mPosts);
    }

    @Override
    public void goToPagePost(Integer id) {
        this.setContentPost(id);
    }

    private void setContentPost(Integer id) {
        PostContentFragment fragment = PostContentFragment.newInstance(id);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_post_content, fragment)
                .commit();
    }

    @Override
    public void goToPostForm(Integer id) {
        Intent intent = new Intent(getContext(), FormPostActivity.class);
        intent.putExtra(PostConstants.ATTRIBUTES.ID, id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    private static class ViewHolder {
        private View mViewDivider;
        private TextView mTextTitlePosts;
        private RecyclerView mRecyclerPosts;
        private ProgressBar mProgressPosts;
        private Snackbar mSnackbar;
    }
}
