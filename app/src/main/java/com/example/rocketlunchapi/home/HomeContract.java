package com.example.rocketlunchapi.home;

import com.example.rocketlunchapi.model.Launch;

import java.util.List;

public interface HomeContract {

    interface View{
        void showLaunches(List<Launch> result);
        void showError(String Message);
    }
    interface Presenter{
        void getLaunchDate(String date);
        void loadAllLaunches();
    }
}
