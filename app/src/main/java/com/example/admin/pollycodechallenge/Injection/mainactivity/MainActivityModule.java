package com.example.admin.pollycodechallenge.Injection.mainactivity;

import com.example.admin.pollycodechallenge.view.mainView.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainPresenter providesMainActivityPresenter(){
        return new MainPresenter();
    }
}
