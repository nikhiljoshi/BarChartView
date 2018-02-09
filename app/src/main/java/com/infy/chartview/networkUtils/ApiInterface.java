package com.infy.chartview.networkUtils;

/**
 * Created by nikhil on 30/1/18.
 */
import com.infy.chartview.model.Graph;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("ipz6h")
    Call<Graph> getGraphData();

}