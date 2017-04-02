package com.nbs.lutfi.trymvpbutterdagger.data.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lutfi on 3/27/2017.
 */
@Module
public class AppModule {
    Application mvpApplication;

    public AppModule(Application mvpApplication){
        this.mvpApplication = mvpApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mvpApplication;
    }
}
