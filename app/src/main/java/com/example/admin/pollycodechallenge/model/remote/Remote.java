package com.example.admin.pollycodechallenge.model.remote;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Francisco on 10/18/2017.
 */

public class Remote {

    private static final String TAG = "Remote";
    private IRemote iremote;

    public Remote(IRemote iremote) {
        this.iremote = iremote;
    }


    /**
     * Get remote data using retrofit call
     */
    //Receive user if we want to search a user here
    public void getTweetkCall(Retrofit retrofit) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.homeTimeline(100, null, null, null,null,null,null);
        iremote.sendCall(call);
    }
}
