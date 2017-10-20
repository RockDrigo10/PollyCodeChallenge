package com.example.admin.pollycodechallenge.Injection.mainactivity.app;

import com.example.admin.pollycodechallenge.App;
import com.example.admin.pollycodechallenge.Injection.mainactivity.remote.RemoteModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, RemoteModule.class})
public interface AppComponent {

    void insert(App app);
    Retrofit getRetrofit();
}
