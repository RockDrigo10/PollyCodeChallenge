package com.example.admin.pollycodechallenge;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.admin.pollycodechallenge.Injection.mainactivity.app.AppComponent;
import com.example.admin.pollycodechallenge.Injection.mainactivity.app.AppModule;
import com.example.admin.pollycodechallenge.Injection.mainactivity.app.DaggerAppComponent;
import com.example.admin.pollycodechallenge.Injection.mainactivity.remote.RemoteModule;
import com.example.admin.pollycodechallenge.util.CONSTANTS;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import io.fabric.sdk.android.Fabric;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        setUpDaggerApp();
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(CONSTANTS.CONSUMER_KEY,CONSTANTS.CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    private void setUpDaggerApp() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).remoteModule(new RemoteModule()).build();

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}