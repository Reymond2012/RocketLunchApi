package com.example.rocketlunchapi.model;

import com.example.rocketlunchapi.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private final DataSource.DataListener listener;
    private final LunchesService lunchesService;

    public RemoteDataSource(DataListener listener) {
        this.listener = listener;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        lunchesService = retrofit.create(LunchesService.class);
    }

    @Override
    public void getLaunchData(String date) {
        lunchesService.getLaunchDetail(date).enqueue(new Callback<RocketResponse>() {
            @Override
            public void onResponse(Call<RocketResponse> call, Response<RocketResponse> response) {
                if(response.isSuccessful()){
                    listener.onLaunchRetrieval(response.body());
                }
            }


            @Override
            public void onFailure(Call<RocketResponse> call, Throwable throwable) {
                listener.onError(throwable);

            }
        });

    }
}
