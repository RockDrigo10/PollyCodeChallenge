package com.example.admin.pollycodechallenge.view.mainView;

import com.example.admin.pollycodechallenge.BasePresenter;
import com.example.admin.pollycodechallenge.BaseView;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Retrofit;

public interface MainContract {

    interface View extends BaseView {
        void sendInfo(List<Tweet> value);
    }

    interface Presenter extends BasePresenter<View> {
        void makeRestCall(Retrofit retrofit);
    }
}
