package com.nbs.lutfi.trymvpbutterdagger.data.component;

import com.nbs.lutfi.trymvpbutterdagger.data.module.AppModule;
import com.nbs.lutfi.trymvpbutterdagger.data.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Lutfi on 3/27/2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
}
