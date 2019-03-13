# WePay_Android
微信支付Android 接入

### 示意图
![image](https://github.com/junchaohjc/WePay_Android/blob/master/pic/wepay.png)
简单来书是服务端下单，客户端获取服务端返回的下单参数，拉起微信支付的SDK

代码中提供客户端和服务端https接口
`HttpsPostThread`
拉起支付回调Activity
`WXPayEntryActivity`
