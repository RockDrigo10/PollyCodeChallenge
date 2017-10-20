package com.example.admin.pollycodechallenge.view.mainView;

import com.example.admin.pollycodechallenge.BasePresenter;
import com.example.admin.pollycodechallenge.BaseView;
import com.example.admin.pollycodechallenge.model.TweetM;

import retrofit2.Retrofit;

public interface MainContract {

    interface View extends BaseView {
        void sendInfo(TweetM value);
    }

    interface Presenter extends BasePresenter<View> {
        void makeRestCall(Retrofit retrofit);
    }
}
