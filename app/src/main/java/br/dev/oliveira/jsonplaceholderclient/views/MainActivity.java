package br.dev.oliveira.jsonplaceholderclient.views;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.dev.oliveira.jsonplaceholderclient.R;
import br.dev.oliveira.jsonplaceholderclient.adapters.PostsListAdapter;
import br.dev.oliveira.jsonplaceholderclient.constants.PostConstants;
import br.dev.oliveira.jsonplaceholderclient.contracts.Base;
import br.dev.oliveira.jsonplaceholderclient.contracts.PostsContract;
import br.dev.oliveira.jsonplaceholderclient.listeners.OnListClickInteractionListener;
import br.dev.oliveira.jsonplaceholderclient.models.Post;
import br.dev.oliveira.jsonplaceholderclient.presenters.PostPresenter;
import br.dev.oliveira.jsonplaceholderclient.receivers.ConnectivityChangeReceiver;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener,
                    View.OnClickListener,
                    PostsContract.View {

    private ViewHolder mViewHolder = new ViewHolder();
    private PostsListAdapter adapter;
    private PostsContract.Presenter mPresenter;
    private List<Post> mPosts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.mPresenter = new PostPresenter(this);

        // registrando receiver que captura a mudança de conexão
        /*registerReceiver(
                new ConnectivityChangeReceiver(),
                new IntentFilter(
                        ConnectivityManager.CONNECTIVITY_ACTION)
        );*/





































































































        this.mViewHolder.fab = findViewById(R.id.fab);
        // Capturando recycler view
        this.mViewHolder.mRecyclerPosts = findViewById(R.id.recycler_posts);

        // Setando o layout manager do recyclerview
        this.mViewHolder.mRecyclerPosts.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        /* Adapter
            O adapter recebe  o contexto da activity
            a listagem e posts que irá exibir
            e um listener para realizar as devidas operações com os itens

            A interface OnListClickInteractionListener obriga a implementação dos métodos
            onClickShow() e onClickDelete() que serão acionado nos clicks dos respectivos
            botões
        */
        OnListClickInteractionListener listener = new OnListClickInteractionListener() {
            @Override
            public void onClickShow(Integer postId) {
                MainActivity.this.mPresenter.goToPagePost(postId);
            }

            @Override

            public void onClickDelete(Integer postId) {

            }
        };

        adapter = new PostsListAdapter(this, this.mPosts, listener);
        this.mViewHolder.mRecyclerPosts.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        this.setListeners();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getPosts(this.mPosts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_new) {
            this.mPresenter.goToPostForm(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showMessageDialog(int title, int message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void getPosts(List<Post> post) {
        this.mPresenter.getPosts(post);
    }

    @Override
    public void goToPagePost(Integer id) {
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        intent.putExtra(PostConstants.ATTRIBUTES.ID, id);
        startActivity(intent);
    }

    @Override
    public void goToPostForm(Integer id) {
        Intent intent = new Intent(MainActivity.this, FormPostActivity.class);
        intent.putExtra(PostConstants.ATTRIBUTES.ID, id);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) this.mPresenter.goToPostForm(null);
    }

    private void setListeners () {
        this.mViewHolder.fab.setOnClickListener(this);
    }

    private static class ViewHolder {
        RecyclerView mRecyclerPosts;
        FloatingActionButton fab;
    }
}
