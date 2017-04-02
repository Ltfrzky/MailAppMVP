package com.nbs.lutfi.trymvpbutterdagger.mainscreen;

import com.nbs.lutfi.trymvpbutterdagger.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lutfi on 3/27/2017.
 */
@Module
public class MainScreenModule {
    private final MainScreenContract.View mvpView;

    public MainScreenModule(MainScreenContract.View mvpView){
        this.mvpView = mvpView;
    }

    @Provides
    @CustomScope
    MainScreenContract.View provideMainScreenContractView(){
        return mvpView;
    }
}
