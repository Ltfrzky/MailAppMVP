package com.nbs.lutfi.trymvpbutterdagger.mainscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nbs.lutfi.trymvpbutterdagger.App;
import com.nbs.lutfi.trymvpbutterdagger.R;
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

    @Inject
    MainScreenPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
}
