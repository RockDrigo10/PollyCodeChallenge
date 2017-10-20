package com.example.admin.pollycodechallenge.model.remote;

import com.example.admin.pollycodechallenge.model.TweetM;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by Francisco on 10/18/2017.
 */

public interface IRemote {

    void sendCall(Call<TweetM> call);

    void sendObservable(Observable<TweetM> TweetObservable);
}
