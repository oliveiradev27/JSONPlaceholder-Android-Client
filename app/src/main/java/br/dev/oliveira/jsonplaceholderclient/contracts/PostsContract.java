package br.dev.oliveira.jsonplaceholderclient.contracts;


import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.models.Post;

public interface PostsContract {
    public interface View {
        void showDialog(int title, int message);
        void showProgressBar();
        void hideProgressBar();
        void getPosts(List<Post> post);
        Context getContext();
        void add();
    }

    public interface Presenter {
        void getPosts(List<Post> posts);
        void add();
    }
}
