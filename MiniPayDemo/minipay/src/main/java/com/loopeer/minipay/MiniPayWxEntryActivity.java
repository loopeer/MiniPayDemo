package com.loopeer.minipay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public abstract class MiniPayWxEntryActivity extends Activity implements IWXAPIEventHandler {
    private final static String PAY_CANCEL_MESSAGE = "cancel";
    private final static String PAY_SUCCESS_MESSAGE = "success";

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        api = WXAPIFactory.createWXAPI(this, MiniPay.getAppId(), false);

        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
            WXLaunchMiniProgram.Resp launchMiniProResp = (WXLaunchMiniProgram.Resp) baseResp;
            onPayResult(launchMiniProResp);
            this.finish();
        }
    }

    public void onPayResult(WXLaunchMiniProgram.Resp result) {
        if (PAY_CANCEL_MESSAGE.equals(result.extMsg)) {
            payCancelResult(result);
        } else if (PAY_SUCCESS_MESSAGE.equals(result.extMsg)) {
            paySuccessResult(result);
        }
    }

    protected abstract void paySuccessResult(WXLaunchMiniProgram.Resp result);

    protected abstract void payCancelResult(WXLaunchMiniProgram.Resp result);
}
