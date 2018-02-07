package br.dev.oliveira.jsonplaceholderclient.contracts;

import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.models.User;

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
    }

    interface Presenter {
        void getUsers();
        void fillListUsername();
        void setUserIdByPosition(int position);
        void showMessageDialog(int title, int message);
        void onBackPressed();
        void showProgressBar();
        void hideProgressBar();
        void save(String title, String body);
    }
}
