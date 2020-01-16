package com.loopeer.minipay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static android.provider.UserDictionary.Words.APP_ID;

public class MiniPay {
    private static final String DEFAULT_MINI_ORIGINAL_ID = "gh_6171153c0770";
    private static final String PATH = "pages/pay/pay";

    private String appId;
    private Context context;
    private String appName;

    private static MiniPay sMiniPay;

    public static void init(Context context, String appId) {
        if (sMiniPay == null) {
            sMiniPay = new MiniPay(context, appId);
            sMiniPay.register();
        }
    }

    static String getAppId() {
        return sMiniPay.appId;
    }

    private MiniPay(Context context, String appId) {
        this.appId = appId;
        this.context = context;
        this.appName = context.getResources().getString(R.string.app_name);
    }

    private void register() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        final IWXAPI api = WXAPIFactory.createWXAPI(context, appId, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 将该app注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }

    /**
     *
     * @param prepayId 预支付ID
     */
    public static void pay(String prepayId) {
        pay(prepayId, DEFAULT_MINI_ORIGINAL_ID, WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE);
    }

    /**
     *
     * @param prepayId 预支付ID
     * @param miniProgramType 可选打开 开发版，体验版和正式版
     *
     * 开发版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_TEST}
     * 体验版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_PREVIEW}
     * 正式版 {@link WXLaunchMiniProgram.Req#MINIPTOGRAM_TYPE_RELEASE}
     */
    public static void pay(String prepayId, int miniProgramType) {
        pay(prepayId, DEFAULT_MINI_ORIGINAL_ID, miniProgramType);
    }

    /**
     *
     * @param prepayId 预支付ID
     * @param miniOriginalId 填小程序原始id
     */
    public static void pay(String prepayId, String miniOriginalId) {
        sMiniPay.miniPay(prepayId
                , TextUtils.isEmpty(miniOriginalId) ? DEFAULT_MINI_ORIGINAL_ID : miniOriginalId
                , WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE);
    }

    /**
     *
     * @param prepayId 预支付ID
     * @param miniOriginalId 填小程序原始id
     * @param miniProgramType 可选打开 开发版，体验版和正式版
     *
     * 开发版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_TEST}
     * 体验版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_PREVIEW}
     * 正式版 {@link WXLaunchMiniProgram.Req#MINIPTOGRAM_TYPE_RELEASE}
     */
    public static void pay(String prepayId, String miniOriginalId, int miniProgramType) {
        sMiniPay.miniPay(prepayId, TextUtils.isEmpty(miniOriginalId) ? DEFAULT_MINI_ORIGINAL_ID : miniOriginalId, miniProgramType);
    }

    /**
     *
     * @param prepayId 预支付ID
     * @param miniOriginalId 填小程序原始id
     * @param miniProgramType 可选打开 开发版，体验版和正式版
     *
     * 开发版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_TEST}
     * 体验版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_PREVIEW}
     * 正式版 {@link WXLaunchMiniProgram.Req#MINIPTOGRAM_TYPE_RELEASE}
     */
    private void miniPay(String prepayId, String miniOriginalId, int miniProgramType) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = miniOriginalId; // 填小程序原始id
        req.path = PATH + "?prepay_id=" + prepayId + "&app_name=" + appName; //拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
        req.miniprogramType = miniProgramType;// 可选打开 开发版，体验版和正式版
        api.sendReq(req);
    }
}
