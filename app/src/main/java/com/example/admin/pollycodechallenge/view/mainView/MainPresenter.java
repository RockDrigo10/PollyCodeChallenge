package com.example.admin.pollycodechallenge.view.mainView;

import android.content.Context;

import com.example.admin.pollycodechallenge.model.TweetM;
import com.example.admin.pollycodechallenge.model.remote.IRemote;
import com.example.admin.pollycodechallenge.model.remote.Remote;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter, IRemote {

    MainContract.View view;
    Context context;
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
    public void sendCall(Call<TweetM> call) {

    }

    @Override
    public void sendObservable(Observable<TweetM> TweetObservable) {
        TweetObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<TweetM>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TweetM value) {
                        view.sendInfo(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void makeRestCall(Retrofit retrofit) {
        iRemote = new Remote(this);
        iRemote.getTweetObs(retrofit);
    }
}
