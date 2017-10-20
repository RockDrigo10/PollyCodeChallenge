package com.example.admin.pollycodechallenge.view.loginview;

import android.content.Context;

import com.example.admin.pollycodechallenge.BasePresenter;
import com.example.admin.pollycodechallenge.BaseView;
import com.google.firebase.auth.FirebaseAuth;

public interface LoginActivityContract {

    interface View extends BaseView {

    }


    interface Presenter extends BasePresenter<View> {
        void removeAuthStateListener();
        void init(Context context, FirebaseAuth mAuth, FirebaseAuth.AuthStateListener mAuthListener);

    }

}
