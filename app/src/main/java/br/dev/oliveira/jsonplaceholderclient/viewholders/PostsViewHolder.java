package br.dev.oliveira.jsonplaceholderclient.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnListClickInteractionListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;

public class PostsViewHolder extends RecyclerView.ViewHolder{

    private TextView mTextPostTitle;
    private ImageView mImageShowPost;
    private ImageView mImageDeletePost;

    public PostsViewHolder(View itemView) {
        super(itemView);
        this.mTextPostTitle = itemView.findViewById(R.id.text_post_title);
        this.mImageShowPost = itemView.findViewById(R.id.image_show_post);
        this.mImageDeletePost = itemView.findViewById(R.id.image_delete_post);
    }

    public void bindData(final Post post, final OnListClickInteractionListener listener) {

        this.mTextPostTitle.setText(post.getTitle());

        this.mImageShowPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickShow(post.getId());
            }
        });

        this.mImageDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDelete(post.getId());
            }
        });

    }


}
