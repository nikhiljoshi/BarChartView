package com.niks.chartview.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nikhil on 30/1/18.
 */

public class Graph implements Serializable
{

    @SerializedName("graph")
    private List<GraphValues> graph = null;

    public List<GraphValues> getGraph() {
        return graph;
    }

    public void setGraph(List<GraphValues> graph) {
        this.graph = graph;
    }

}