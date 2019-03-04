package com.example.rocketlunchapi.model;

import com.example.rocketlunchapi.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LunchesService {

    @GET(Constants.ENDPOINT)
    Call<RocketResponse> getLaunchDetail(@Path("launchDate")String date);
}
