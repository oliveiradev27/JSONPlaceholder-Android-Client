package br.dev.oliveira.jsonplaceholderclient.contracts;

import android.content.Context;

import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.models.User;

public interface FormPostContract {

    interface Model {

    }

    interface View {
        void getUsers(List<User> users);
        Context getContext();
        void showMessageDialog(int title, int message);
    }

    interface Presenter {
        void getUsers(List<User> users);
    }
}
