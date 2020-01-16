package com.loopeer.android.apps.masterplus4android.net.service;

import com.loopeer.android.apps.masterplus4android.model.Result;
import com.loopeer.android.apps.masterplus4android.net.ApiService;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PayService {

    PayService INSTANCE = ApiService.getRetrofit().create(PayService.class);

    @FormUrlEncoded
    @POST("pay/create")
    Flowable<Result> payCreate(
            @Field("holder") String holder
    );
}
