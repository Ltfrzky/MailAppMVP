package com.nbs.lutfi.trymvpbutterdagger;

import android.app.Application;

import com.nbs.lutfi.trymvpbutterdagger.data.component.DaggerNetComponent;
import com.nbs.lutfi.trymvpbutterdagger.data.component.NetComponent;
import com.nbs.lutfi.trymvpbutterdagger.data.module.AppModule;
import com.nbs.lutfi.trymvpbutterdagger.data.module.NetModule;

/**
 * Created by Lutfi on 3/27/2017.
 */

public class App extends Application {
    private NetComponent mvpNetComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        mvpNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://api.androidhive.info/json/"))
                .build();
    }
    public NetComponent getMvpNetComponent(){
        return mvpNetComponent;
    }
}
