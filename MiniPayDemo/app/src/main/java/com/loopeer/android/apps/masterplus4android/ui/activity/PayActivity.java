package com.loopeer.android.apps.masterplus4android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.loopeer.android.apps.masterplus4android.R;
import com.loopeer.android.apps.masterplus4android.net.service.PayService;
import com.loopeer.android.apps.masterplus4android.ui.base.BaseActivity;
import com.loopeer.android.apps.masterplus4android.utils.L;
import com.loopeer.minipay.MiniPay;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PayActivity extends BaseActivity {

    Disposable mDisposable;

    private PayService mPayService = PayService.INSTANCE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        getSupportActionBar().setTitle("支付订单");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void onPayClick(View view) {
        payPreview();
    }

    private void payPreview() {
        mDisposable = mPayService
                .payCreate("todo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    MiniPay.pay(result.prepayId, WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW);
                    this.finish();
                }, throwable -> L.e(throwable));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
