package br.dev.oliveira.jsonplaceholderclient.views;

import android.app.ProgressDialog;
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
import br.dev.oliveira.jsonplaceholderclient.constants.PostConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.FormPostContract;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.presenters.FormPostPresenter;

public class FormPostActivity extends AppCompatActivity
        implements FormPostContract.View,
        View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private FormPostContract.Presenter mPresenter;
    private List<String> mListUsernames = new ArrayList<>();
    private ArrayAdapter<String> mAdapterUsers;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_post);

        this.mPresenter = new FormPostPresenter(this);
        this.getPost();

        this.mViewHolder.mEditTitle    = findViewById(R.id.edit_title);
        this.mViewHolder.mEditBody     = findViewById(R.id.edit_body);
        this.mViewHolder.mSpinnerUsers = findViewById(R.id.spinner_users);
        this.mViewHolder.mButtonSave   = findViewById(R.id.button_save_post);
        this.mViewHolder.mButtonCancel = findViewById(R.id.button_cancel);

        this.setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mPresenter.getUsers();

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
        this.mPresenter.getUsers();
    }

    @Override
    public List<String> getListUsernames() {
        return this.mListUsernames;
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
        //this.mViewHolder.mSpinnerUsers.requestFocus();
        new AlertDialog.Builder(this)
                .setTitle(R.string.mensagem_do_sistema)
                .setMessage(getString(R.string.id_obrigatorio))
                .show();
    }

    @Override
    public void showProgressBar() {
        if (mDialog == null || !mDialog.isShowing()) {
            mDialog = new ProgressDialog(this);
            mDialog.setTitle(R.string.mensagem_do_sistema);
            mDialog.setMessage(getString(R.string.carregando));
            mDialog.setCancelable(true);
            mDialog.show();
        }
    }

    @Override
    public void hideProgressBar() {

        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();

    }

    @Override
    public void save() {

        String title = this.mViewHolder.mEditTitle.getText().toString();
        String body = this.mViewHolder.mEditBody.getText().toString();

        this.mPresenter.save(title, body);

    }


    @Override
    public void bindPostData(Post post) {
        this.mViewHolder.mSpinnerUsers.setSelection(mPresenter.getPositionByUserId(post.getUserId()));
        this.mViewHolder.mEditTitle.setText(post.getTitle());
        this.mViewHolder.mEditBody.setText(post.getBody());
    }

    @Override
    public void getPost() {
        this.mPresenter.getPost();
    }

    private void setListeners() {
        this.mViewHolder.mButtonSave.setOnClickListener(this);
        this.mViewHolder.mButtonCancel.setOnClickListener(this);
        this.mViewHolder.mSpinnerUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    mPresenter.setUserIdByPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public Integer getPostId() {
        return getIntent().getIntExtra(PostConstants.ATTRIBUTES.ID, 0);
    }


    public void setAdapterUsers() {
        this.mAdapterUsers = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                mListUsernames
        );

        this.mAdapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mViewHolder.mSpinnerUsers.setAdapter(mAdapterUsers);
        this.mAdapterUsers.notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextInputEditText mEditTitle, mEditBody;
        Spinner mSpinnerUsers;
        Button mButtonSave, mButtonCancel;
    }

}
