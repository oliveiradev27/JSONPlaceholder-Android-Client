package br.dev.oliveira.jsonplaceholderclient.contracts;

import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.models.User;

public interface FormPostContract {

    interface View {
        void fillListUsername();
        Context getContext();
        void showMessageDialog(int title, int message);
        void validateInputBody();
        void validateInputTitle();
        void validateInputUserId();
        void onBackPressed();
        void save();
    }

    interface Presenter {
        void getUsers();
        void fillListUsername(List<String> listUsername);
        void setUserIdByPosition(int position);
        void showMessageDialog(int title, int message);
        void onBackPressed();
        void save(String title, String body);
    }
}
