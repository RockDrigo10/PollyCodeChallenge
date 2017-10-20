
package com.example.admin.pollycodechallenge.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMention {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("indices")
    @Expose
    private List<Integer> indices = null;
    @SerializedName("screen_name")
    @Expose
    private String screenName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

}
