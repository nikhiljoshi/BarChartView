package com.infy.chartview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.infy.chartview.R;
import com.infy.chartview.model.Graph;
import com.infy.chartview.model.GraphValues;
import com.infy.chartview.networkUtils.ApiClient;
import com.infy.chartview.networkUtils.ApiInterface;
import com.infy.chartview.view.ChartView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nikhil on 30/1/18.
 */


public class MainActivity extends AppCompatActivity {

    private ChartView mChartView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChartView = (ChartView) findViewById(R.id.chart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }


    /* To fetch data from Network using Retrofit Library and initilize model class*/
    private void fetchData()
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        mChartView.clear();
        Call<Graph> call = apiService.getGraphData();
        call.enqueue(new Callback<Graph>() {
            @Override
            public void onResponse(Call<Graph>call, Response<Graph> response) {
                List<GraphValues> graph = response.body().getGraph();
                Log.d("MainActivity", "graph.size() of  received: " +graph.size());
                for(GraphValues graph1 : graph)
                {
                    Log.d("MainActivity", "getIndex==" +graph1.getIndex() +"\t getValue=" +graph1.getValue());
                    mChartView.setIndexList(String.valueOf(graph1.getIndex()));
                    mChartView.setValueList(graph1.getValue());
                }
                mChartView.refreshData();

            }

            @Override
            public void onFailure(Call<Graph>call, Throwable t) {
                Log.e("MainActivity", t.toString());
            }
        });
    }



    /*API to set Background color of a Bar View */
    private void setBackgroundColor(int color)
    {
        RelativeLayout  activity_main  = (RelativeLayout) findViewById(R.id.activity_main);
        activity_main.setBackgroundColor(color);

    }

    /*API to set Bar color of a Bar View */
    private void setBarColor(int color)
    {

        mChartView.setBarColor(color);
        mChartView.invalidate();
    }


}
