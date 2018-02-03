package br.dev.oliveira.jsonplaceholderclient.contracts;

import android.content.Context;

import br.dev.oliveira.jsonplaceholderclient.models.Post;

public interface PostViewContract {

    interface View {
        void showMessageDialog(int title, int message);
        void showConfirmAction(int message);
        void showProgressBar();
        void hideProgressBar();
        void getPost();
        Context getContext();

    }

    interface Presenter {
        void showMessageDialog(int title, int message);
        void showConfirmAction(int message);
        void showProgressBar();
        void hideProgressBar();
        void getPost(Post post);
    }
}
