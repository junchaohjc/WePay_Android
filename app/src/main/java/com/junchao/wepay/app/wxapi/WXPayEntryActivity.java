package com.junchao.wepay.app.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private  String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wxpay_entry);

        api = WXAPIFactory.createWXAPI(this, "1111111111");
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        TextView textView=(TextView) findViewById(R.id.resultget);
        Log.d(TAG, "errCode = " + resp.errCode);
        if (resp.errCode==-1){
            textView.setText("支付错误"+"\n"+"检查appid是否为: 11111111"+"\n");
        }
        if (resp.errCode==0){
            textView.setText("success");
        }
        if(resp.errCode==-2){
            textView.setText("取消支付，请再次下单");
        }
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

        }
    }
}

