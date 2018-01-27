package br.dev.oliveira.jsonplaceholderclient.contracts;


import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.listeners.OnListClickInteractionListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;

public interface PostsContract {

    interface Model {
        void getPosts(List<Post> posts, OnListClickInteractionListener listener);
        void post(Integer postId);
        void add(Post post);
        void del(Integer postId);
        void upd(Post post);
    }

    interface View {
        void showDialog(int title, int message);
        void showProgressBar();
        void hideProgressBar();
        void getPosts(List<Post> post);
        Context getContext();
        void add();
    }

    interface Presenter {
        void getPosts(List<Post> posts);
        void add();
    }
}
