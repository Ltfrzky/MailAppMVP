package com.nbs.lutfi.trymvpbutterdagger.mainscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nbs.lutfi.trymvpbutterdagger.App;
import com.nbs.lutfi.trymvpbutterdagger.R;
import com.nbs.lutfi.trymvpbutterdagger.activity.LoginActivity;
import com.nbs.lutfi.trymvpbutterdagger.adapter.MailAdapter;
import com.nbs.lutfi.trymvpbutterdagger.data.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    List<Post> mailList = new ArrayList<>();
    @BindView(R.id.mail_rv) RecyclerView mailRV;
    MailAdapter mailAdapter;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Inject
    MainScreenPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                    return;
                }
            }
        };


        mailAdapter = new MailAdapter(mailList,this);
        mailRV.setLayoutManager(new LinearLayoutManager(this));

        DaggerMainScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getMvpNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        mvpPresenter.loadMails();
//        mvpPresenter.loadPosts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            logout();
            return true;
        }

        if (id == android.R.id.home){
//            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    private void logout(){
        auth.signOut();
    }

    @Override
    public void showResponse(Call<List<Post>> call, Response<List<Post>> response){
        List<Post> mailPost = response.body();
        mailList.addAll(mailPost);
        mailRV.setAdapter(mailAdapter);
    }

    @Override
    public void showFailure(Call<List<Post>> call, Throwable t){
        Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPosts(List<Post> posts) {
//        for (int i = 0; i < posts.size(); i++) {
//            list.add(posts.get(i).getSubject());
//        }
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getApplicationContext(), "Error" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
