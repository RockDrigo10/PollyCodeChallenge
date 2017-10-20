package com.example.admin.pollycodechallenge.model.remote;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Francisco on 10/18/2017.
 */

public interface IRemote {

    void sendCall(Call<List<Tweet>> call);
}
