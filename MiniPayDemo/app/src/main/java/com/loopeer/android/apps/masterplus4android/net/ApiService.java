package com.loopeer.android.apps.masterplus4android.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private Retrofit mRetrofit;

    private static ApiService mInstance;

    public static ApiService getInstance() {
        if (mInstance == null) {
            mInstance = new ApiService();
        }
        return mInstance;
    }

    public ApiService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://voice.geizan.cc/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return getInstance().mRetrofit;
    }
}
