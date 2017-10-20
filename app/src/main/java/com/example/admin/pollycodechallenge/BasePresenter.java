package com.example.admin.pollycodechallenge;

public interface BasePresenter <V extends BaseView>{

    void attachView(V view);
    void detachView();

}
