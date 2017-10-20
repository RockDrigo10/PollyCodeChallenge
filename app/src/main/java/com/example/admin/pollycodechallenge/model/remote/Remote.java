package com.example.admin.pollycodechallenge.model.remote;

import com.example.admin.pollycodechallenge.model.TweetM;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import static com.example.admin.pollycodechallenge.util.CONSTANTS.BASE_PATH;

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
        IResponse iResponse = retrofit.create(IResponse.class);
        iremote.sendCall(iResponse.getTweet());
    }

    /**
     * Get remote data using rxjava observable
     */
    public void getTweetObs(final Retrofit retrofit) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<Tweet> call = statusesService.show(524971209851543553L, null, null, null);
        call.enqueue(new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                //Do something with result
                IResponse iResponse = retrofit.create(IResponse.class);
                iremote.sendObservable(iResponse.getTweetObservable());
            }

            public void failure(TwitterException exception) {
                //Do something on failure
            }
        });
    }


    public interface IResponse {

        @GET(BASE_PATH)
        Call<TweetM> getTweet();

        @GET(BASE_PATH)
        Observable<TweetM> getTweetObservable();

    }
}
