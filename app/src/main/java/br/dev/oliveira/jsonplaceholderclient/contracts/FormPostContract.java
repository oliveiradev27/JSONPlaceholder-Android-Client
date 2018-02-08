package br.dev.oliveira.jsonplaceholderclient.contracts;

import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.models.Post;

public interface FormPostContract {

    interface View {
        void fillListUsername();

        Context getContext();

        void showMessageDialog(int title, int message);

        void showProgressBar();

        void hideProgressBar();

        void validateInputBody();

        void validateInputTitle();

        void validateInputUserId();

        void onBackPressed();

        List<String> getListUsernames();

        void save();

        void setAdapterUsers();

        Integer getPostId();

        void getPost();

        void bindPostData(Post post);
    }

    interface Presenter {
        void getUsers();

        void fillListUsername();

        void setUserIdByPosition(int position);

        int getPositionByUserId(int userId);

        void showMessageDialog(int title, int message);

        void onBackPressed();

        void showProgressBar();

        void hideProgressBar();

        void save(String title, String body);

        void getPost();
    }
}
