# MiniPay

[toc]

## Android接入步骤

### 1. Gradle引入

```groovy
implementation 'com.loopeer.lib:minipay:0.0.2'
```

### 2. 初始化

```java
MiniPay.init(getContext(), "微信appid");
```

### 3. 调起支付

```java
/**
* @param prepayId 预支付ID
* 默认调起小程序gh_6171153c0770
* 打开类型WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE
**/
MiniPay.pay(String prepayId);

/**
* @param prepayId 预支付ID
* @param miniProgramType 可选打开 开发版，体验版和正式版
* 
* 默认调起小程序gh_6171153c0770
*
* 开发版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_TEST}
* 体验版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_PREVIEW}
* 正式版 {@link WXLaunchMiniProgram.Req#MINIPTOGRAM_TYPE_RELEASE}
**/
MiniPay.pay(String prepayId, int miniProgramType);

/**
* @param prepayId 预支付ID
* @param miniOriginalId 填小程序原始id
*
* 打开类型WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE
**/
MiniPay.pay(String prepayId, String miniOriginalId);

/**
* @param prepayId 预支付ID
* @param miniOriginalId 填小程序原始id
* @param miniProgramType 可选打开 开发版，体验版和正式版
* 
* 开发版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_TEST}
* 体验版 {@link WXLaunchMiniProgram.Req#MINIPROGRAM_TYPE_PREVIEW}
* 正式版 {@link WXLaunchMiniProgram.Req#MINIPTOGRAM_TYPE_RELEASE}
**/
MiniPay.pay(String prepayId, String miniOriginalId, int miniProgramType);
```

### 4. 小程序回调接收
添加WXEntryActivity继承自MiniPayWxEntryActivity，WXEntryActivity添加位置为“app包名.wxapi“下. 例如ApplicationId及app包名为com.loopeer.test, 那么就在com.loopeer.test.wxapi创建WXEntryActivity这个类，名称不能修改，否则回调不到

```java
public class WXEntryActivity extends MiniPayWxEntryActivity {

    @Override
    public void onPayResult(WXLaunchMiniProgram.Resp result) {
        //TODO 根据实际情况处理返回结果
        startActivity(new Intent(this, DetailActivity.class));
    }

}
```

同时在AndroidManifest.xml添加如下配置
```xml
<activity
    android:name=".wxapi.WXEntryActivity"
    android:label="@string/app_name"
    android:theme="@android:style/Theme.Translucent.NoTitleBar"
    android:exported="true"
    android:taskAffinity="对应app的ApplicationId(build.gradle里的那个)"
    android:launchMode="singleTask">
</activity>
```