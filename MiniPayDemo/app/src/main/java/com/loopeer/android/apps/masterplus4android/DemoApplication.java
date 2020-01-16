package com.loopeer.android.apps.masterplus4android;

import android.content.Context;
import androidx.multidex.MultiDexApplication;

import com.loopeer.android.apps.masterplus4android.net.ApiService;
import com.loopeer.minipay.MiniPay;

public class DemoApplication extends MultiDexApplication {

    private static Context sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        init();
    }

    public static Context getContext() {
        return sInstance;
    }

    private void init() {
        regToWx();
        initNet();
    }

    private void regToWx() {
        MiniPay.init(getContext(), "wx0595bdf569c63d1e");
    }

    private void initNet() {
        ApiService.getInstance();
    }
}
