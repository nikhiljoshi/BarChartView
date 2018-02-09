package com.infy.chartview.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by nikhil on 30/1/18.
 */


public class GraphValues implements Serializable
{

    @SerializedName("index")
    private Integer index;

    @SerializedName("value")
    private Integer value;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}