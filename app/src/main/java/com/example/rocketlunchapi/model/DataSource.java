package com.example.rocketlunchapi.model;

public interface DataSource {

    void getLaunchData(String date);

    interface DataListener{
        void onLaunchRetrieval(RocketResponse rocketResponse);
        void onError(Throwable throwable);
    }
}
