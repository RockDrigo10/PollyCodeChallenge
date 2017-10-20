package com.example.admin.pollycodechallenge.Injection.mainactivity.remote;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RemoteModule.class})
public interface RemoteComponent {

    Retrofit getRetrofit();
}