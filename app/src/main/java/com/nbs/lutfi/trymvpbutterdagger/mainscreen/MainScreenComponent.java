package com.nbs.lutfi.trymvpbutterdagger.mainscreen;

import android.app.Activity;

import com.nbs.lutfi.trymvpbutterdagger.data.component.NetComponent;
import com.nbs.lutfi.trymvpbutterdagger.util.CustomScope;

import dagger.Component;

/**
 * Created by Lutfi on 3/27/2017.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
