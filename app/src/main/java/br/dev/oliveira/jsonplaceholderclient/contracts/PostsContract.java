package br.dev.oliveira.jsonplaceholderclient.contracts;


import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.listeners.OnResponseRequestListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;

public interface PostsContract {

    interface View {
        void showMessageDialog(int title, int message);
        void showConfirmAction(int message);
        void showProgressBar();
        void hideProgressBar();
        void getPosts();
        Context getContext();
        void goToPagePost(Integer id);
        void goToPostForm(Integer id);
        void onBackPressed();

    }

    interface Presenter {
        void getPosts(List<Post> posts);
        void goToPagePost(Integer id);
        void goToPostForm(Integer id);
        void showProgressBar();
        void hideProgressBar();
        void onBackPressed();
        void showMessageDialog(int title, int message);
        void delete();
        void showConfirmAction(Integer postId);
    }

}
