package com.nbs.lutfi.trymvpbutterdagger.mainscreen;

import com.nbs.lutfi.trymvpbutterdagger.data.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lutfi on 3/27/2017.
 */

public interface MainScreenContract {
    interface View{
        void showPosts(List<Post> posts);

        void showError(String msg);

        void showComplete();

        void showResponse(Call<List<Post>> call, Response<List<Post>> response);

        void showFailure(Call<List<Post>> call, Throwable t);
    }

    interface Presenter{
        void loadMails();
        void loadPosts();
    }
}
