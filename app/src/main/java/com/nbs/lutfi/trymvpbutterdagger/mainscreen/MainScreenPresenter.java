package com.nbs.lutfi.trymvpbutterdagger.mainscreen;

import com.nbs.lutfi.trymvpbutterdagger.data.Post;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lutfi on 3/27/2017.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {
    public Retrofit retrofit;
    MainScreenContract.View mvpView;


    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View mvpView) {
        this.retrofit = retrofit;
        this.mvpView = mvpView;
    }

    @Override
    public void loadMails(){
        PostService mailService = retrofit.create(PostService.class);
        Call<List<Post>> call = mailService.getMailList();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mvpView.showResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mvpView.showFailure(call,t);
            }
        });
    }

    @Override
    public void loadPosts() {
        retrofit.create(PostService.class).getPostList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {

                    @Override
                    public void onCompleted() {
                        mvpView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        mvpView.showPosts(posts);
                    }
                });
    }

    public interface PostService {
        @GET("inbox.json")
        Observable<List<Post>> getPostList();

        @GET("inbox.json")
        Call<List<Post>> getMailList();
    }
}
