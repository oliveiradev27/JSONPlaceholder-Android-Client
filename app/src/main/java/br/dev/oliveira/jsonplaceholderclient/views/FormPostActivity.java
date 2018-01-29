package br.dev.oliveira.jsonplaceholderclient.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.contracts.FormPostContract;
import br.dev.oliveira.jsonplaceholderclient.models.User;
import br.dev.oliveira.jsonplaceholderclient.presenters.FormPostPresenter;

public class FormPostActivity extends AppCompatActivity
        implements FormPostContract.View,
        View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private FormPostContract.Presenter mPresenter;
    private List<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_post);

        this.mPresenter = new FormPostPresenter(this);

        this.mViewHolder.mEditTitle = findViewById(R.id.edit_title);
        this.mViewHolder.mEditBody= findViewById(R.id.edit_body);
        this.mViewHolder.mSpinnerUsers = findViewById(R.id.spinner_users);
        this.mViewHolder.mButtonSave = findViewById(R.id.button_save_post);

        //this.mViewHolder.mSpinnerUsers.setAdapter(new ArrayAdapter<User>());

        this.setListeners();
        this.getUsers(mUsers);
    }

    @Override
    public void getUsers(List<User> users) {
        this.mPresenter.getUsers(users);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessageDialog(int title, int message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    @Override
    public void onClick(View v) {

    }

    private void setListeners() {
        this.mViewHolder.mButtonSave.setOnClickListener(this);
    }


    private static class ViewHolder {
        TextInputEditText mEditTitle, mEditBody;
        Spinner mSpinnerUsers;
        Button mButtonSave, mButtonCancel;
    }
}
