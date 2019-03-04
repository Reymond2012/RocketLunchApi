package com.example.rocketlunchapi.home;

import com.example.rocketlunchapi.model.DataSource;
import com.example.rocketlunchapi.model.RemoteDataSource;
import com.example.rocketlunchapi.model.RocketResponse;

public class HomePresenter implements HomeContract.Presenter, DataSource.DataListener {

    private final HomeContract.View view;
    private final DataSource dataSource;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        this.dataSource = new RemoteDataSource(this);
    }

    @Override
    public void getLaunchDate(String date) {
        dataSource.getLaunchData(date);

    }

    @Override
    public void loadAllLaunches() {

    }

    @Override
    public void onLaunchRetrieval(RocketResponse rocketResponse) {
        view.showLaunches(rocketResponse.getLaunches());

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
