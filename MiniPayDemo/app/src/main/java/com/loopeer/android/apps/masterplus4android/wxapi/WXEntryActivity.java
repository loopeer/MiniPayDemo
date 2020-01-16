package com.loopeer.android.apps.masterplus4android.wxapi;

import android.content.Intent;
import android.widget.Toast;

import com.loopeer.android.apps.masterplus4android.ui.activity.DetailActivity;
import com.loopeer.minipay.MiniPayWxEntryActivity;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;

public class WXEntryActivity extends MiniPayWxEntryActivity {

    @Override
    public void onPayResult(WXLaunchMiniProgram.Resp result) {
        startActivity(new Intent(this, DetailActivity.class));
    }

    @Override
    protected void paySuccessResult(WXLaunchMiniProgram.Resp result) {
        startActivity(new Intent(this, DetailActivity.class));
    }

    @Override
    protected void payCancelResult(WXLaunchMiniProgram.Resp result) {
        Toast.makeText(this, "支付已取消", Toast.LENGTH_LONG).show();
    }
}
