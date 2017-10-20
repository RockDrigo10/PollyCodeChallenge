package com.example.admin.pollycodechallenge.Injection.mainactivity;

import com.example.admin.pollycodechallenge.Injection.mainactivity.app.AppComponent;
import com.example.admin.pollycodechallenge.Injection.mainactivity.remote.UserScope;
import com.example.admin.pollycodechallenge.view.mainView.MainView;

import dagger.Component;

@UserScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainView mainActivity);
}
