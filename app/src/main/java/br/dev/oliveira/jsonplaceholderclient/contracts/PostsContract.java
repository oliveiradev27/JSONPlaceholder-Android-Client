package br.dev.oliveira.jsonplaceholderclient.contracts;


import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;

public interface PostsContract {

    interface Model {
        void getPosts(OnResponseRequestListener listener);
        void post(Integer postId);
        void add(Post post);
        void del(Integer postId);
        void upd(Post post);
    }

    interface View {
        void showMessageDialog(int title, int message);
        void showProgressBar();
        void hideProgressBar();
        void getPosts(List<Post> post);
        Context getContext();
        void goToPagePost(Integer id);
        void goToPostForm(Integer id);
    }

    interface Presenter {
        void getPosts(List<Post> posts);
        void goToPagePost(Integer id);
        void goToPostForm(Integer id);
    }
}
