
package com.example.admin.pollycodechallenge.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities {

    @SerializedName("urls")
    @Expose
    private List<Url> urls = null;
    @SerializedName("hashtags")
    @Expose
    private List<Object> hashtags = null;
    @SerializedName("user_mentions")
    @Expose
    private List<UserMention> userMentions = null;

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public List<Object> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Object> hashtags) {
        this.hashtags = hashtags;
    }

    public List<UserMention> getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(List<UserMention> userMentions) {
        this.userMentions = userMentions;
    }

}
