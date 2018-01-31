package br.dev.oliveira.jsonplaceholderclient.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.contracts.FormPostContract;
import br.dev.oliveira.jsonplaceholderclient.presenters.FormPostPresenter;

public class FormPostActivity extends AppCompatActivity
        implements FormPostContract.View,
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private FormPostContract.Presenter mPresenter;
    private List<String> mListUsernames = new ArrayList<>();
    private ArrayAdapter<String> adapterUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_post);

        this.mPresenter = new FormPostPresenter(this);

        this.mPresenter.getUsers();
        this.mPresenter.fillListUsername(this.mListUsernames);

        this.mViewHolder.mEditTitle = findViewById(R.id.edit_title);
        this.mViewHolder.mEditBody = findViewById(R.id.edit_body);
        this.mViewHolder.mSpinnerUsers = findViewById(R.id.spinner_users);
        this.mViewHolder.mButtonSave = findViewById(R.id.button_save_post);
        this.mViewHolder.mButtonCancel = findViewById(R.id.button_cancel);

        this.adapterUsers = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                mListUsernames
        );

        this.adapterUsers.notifyDataSetChanged();
        this.mViewHolder.mSpinnerUsers.setAdapter(adapterUsers);

        this.setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.fillListUsername();
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
        switch (v.getId()) {
            case R.id.button_cancel:
                this.mPresenter.onBackPressed();
                break;
            case R.id.button_save_post:
                this.save();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void fillListUsername() {
        this.mPresenter.fillListUsername(this.mListUsernames);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.mPresenter.setUserIdByPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void validateInputTitle() {
        this.mViewHolder.mEditTitle.setError(getString(R.string.post_titulo_requerido));
    }

    @Override
    public void validateInputBody() {
        this.mViewHolder.mEditBody.setError(getString(R.string.post_corpo_requerido));
    }

    @Override
    public void validateInputUserId() {
        this.mViewHolder.mSpinnerUsers.requestFocus();
        new AlertDialog.Builder(this)
                .setTitle(R.string.ocorreu_um_erro)
                .setMessage(R.string.post_usuario_requerido);
    }

    @Override
    public void save() {

        String title = this.mViewHolder.mEditTitle.getText().toString();
        String body  = this.mViewHolder.mEditBody.getText().toString();

        this.mPresenter.save(title, body);

    }



    private void setListeners() {
        this.mViewHolder.mButtonSave.setOnClickListener(this);
        this.mViewHolder.mButtonCancel.setOnClickListener(this);
        this.mViewHolder.mSpinnerUsers.setOnItemSelectedListener(this);
    }

    private static class ViewHolder {
        TextInputEditText mEditTitle, mEditBody;
        Spinner mSpinnerUsers;
        Button mButtonSave, mButtonCancel;
    }

}
