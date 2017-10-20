package com.example.admin.pollycodechallenge.view.mainView;

import com.example.admin.pollycodechallenge.model.remote.IRemote;
import com.example.admin.pollycodechallenge.model.remote.Remote;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter, IRemote {

    MainContract.View view;
    private static final String TAG = "MainPresenter";

    private Remote iRemote;

    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void sendCall(Call<List<Tweet>> call) {
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                //Do something with result
                view.sendInfo(result.data);
            }

            public void failure(TwitterException exception) {
                //Do something on failure
            }
        });
    }

    @Override
    public void makeRestCall(Retrofit retrofit) {
        iRemote = new Remote(this);
        iRemote.getTweetkCall(retrofit);
    }
}
