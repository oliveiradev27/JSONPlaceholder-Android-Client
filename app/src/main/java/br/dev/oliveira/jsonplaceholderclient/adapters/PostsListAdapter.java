package br.dev.oliveira.jsonplaceholderclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnListClickInteractionListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.viewholders.PostsViewHolder;


public class PostsListAdapter extends RecyclerView.Adapter<PostsViewHolder> {
    private List<Post> mPosts;
    private Context mContext;
    private OnListClickInteractionListener mOnListClickInteractionListener;

    public PostsListAdapter(
            Context context,
            List<Post> posts,
            OnListClickInteractionListener listener
    ) {
        this.mPosts = posts;
        this.mContext = context;
        this.mOnListClickInteractionListener = listener;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Obtém inflater
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Instancia o layout para manipulação dos elementos
        View view = inflater.inflate(R.layout.row_list_posts, parent, false);

        // Passa a ViewHolder
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        Post post = this.mPosts.get(position);
        holder.bindData(post, this.mOnListClickInteractionListener);
    }

    @Override
    public int getItemCount() {
        return this.mPosts.size();
    }
}
