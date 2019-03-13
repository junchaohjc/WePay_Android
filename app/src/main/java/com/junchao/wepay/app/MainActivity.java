package com.junchao.wepay.app;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.junchao.wepay.app.HttpsUtils.HttpsPostThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.Date;



public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private IWXAPI msgApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testButton = (Button)findViewById(R.id.buttonorder);

        msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp("");//注册
        initData();

        //下单 https 方式向服务器下单
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject list = new JSONObject();
                try {
                    list.put("111", 111);   //打包下单json
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpsPostThread thread = new HttpsPostThread(handler, "下单url", list, 200);
                thread.start();
            }
        });
    }


    //支付
    private void initData(){
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = (String) msg.obj;
                switch (msg.what) {
                    case 200:
                        //请求成功
                        Log.e("TAG", "返回参数===" + result);

                        PayReq request = new PayReq();
                        request.appId ="";
                        request.nonceStr = "";
                        request.packageValue = "Sign=WXPay";
                        request.partnerId = "";
                        request.prepayId = "";
                        request.timeStamp = "";
                        request.sign ="";
                        msgApi.sendReq(request);
                    case 404:
                        //请求失败
                        Log.e("TAG", "请求失败!");
                        break;
                }
            }
        };
    }

}
