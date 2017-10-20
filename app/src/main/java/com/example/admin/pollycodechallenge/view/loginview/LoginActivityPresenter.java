package com.example.admin.pollycodechallenge.view.loginview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.admin.pollycodechallenge.view.mainView.MainView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivityPresenter implements LoginActivityContract.Presenter{

    private static final String TAG = "LoginPresenter";
    private LoginActivityContract.View view;
    private Context context;
    //google Fire base stuff
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;


    public void attachView(LoginActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void initializeFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
            }
        };
    }

    @Override
    public void init(Context context, FirebaseAuth mAuth, FirebaseAuth.AuthStateListener mAuthListener) {
        this.mAuth = mAuth;
        this.mAuthListener = mAuthListener;
        this.context = context;
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void goToSecondActivity(loginActivity loginActivity) {
        Intent intent = new Intent(loginActivity, MainView.class);
        loginActivity.startActivity(intent);
    }

    @Override
    public void removeAuthStateListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
