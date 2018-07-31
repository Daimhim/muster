package net.sourceforge.simcpux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.daimhim.onekeypayment.PaymentCallback;
import org.daimhim.onekeypayment.PaymentConst;
import org.daimhim.onekeypayment.ToPay;
import org.daimhim.onekeypayment.model.AlPayParameter;
import org.daimhim.onekeypayment.model.PaymentReponse;
import org.daimhim.onekeypayment.model.PaymentRequest;
import org.daimhim.onekeypayment.model.WxPayParameter;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PaymentCallback {

    /**
     * WxPay
     */
    private Button mBtWxPay;
    /**
     * AlPay
     */
    private Button mBtAlPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtWxPay = (Button) findViewById(R.id.bt_wx_pay);
        mBtWxPay.setOnClickListener(this);
        mBtAlPay = (Button) findViewById(R.id.bt_al_pay);
        mBtAlPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_wx_pay:
                //微信
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String url = "https://wxpay.wxutil.com/pub_v2/app/app_pay.php";
                            byte[] buf = Util.httpGet(url);
                            if (buf != null && buf.length > 0) {
//                                {"appid":"wxb4ba3c02aa476ea1","partnerid":"1900006771","package":"Sign=WXPay","noncestr":"c289be37aa1534d736ee87d484d4f3eb",
// "timestamp":1533052327,"prepayid":"wx312352076736429433f6e8b82798045604","sign":"5FC6F69FCF20CD839775C2BEE69A9471"}
                                String content = new String(buf);
                                Log.e("get server pay params:", content);
                                JSONObject obj = new JSONObject(content);
                                if (!obj.has("retcode")) {
                                    WxPayParameter lWxPayParameter = new WxPayParameter();
                                    lWxPayParameter.setAppId(obj.getString("appid"));
                                    lWxPayParameter.setTimeStamp(obj.getString("timestamp"));
                                    lWxPayParameter.setPackageValue(obj.getString("package"));
                                    lWxPayParameter.setPartnerId(obj.getString("partnerid"));
                                    lWxPayParameter.setNonceStr(obj.getString("noncestr"));
                                    lWxPayParameter.setPrepayId(obj.getString("prepayid"));
                                    lWxPayParameter.setPaySign(obj.getString("sign"));
                                    PaymentRequest lPaymentRequest = new PaymentRequest(lWxPayParameter);
                                    lPaymentRequest.setPayType(PaymentConst.WX_PAY);
                                    ToPay.getInstance().toPayment(MainActivity.this, lPaymentRequest, MainActivity.this);
                                }
                            }
                        } catch (Exception e) {
                            Log.e("PAY_GET", "异常：" + e.getMessage());
                        }
                    }
                }).start();
                break;
            case R.id.bt_al_pay:
                //支付宝
                AlPayParameter lAlPayParameter = new AlPayParameter();

//                keyValues.put("app_id", app_id);
//
//                keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"" + getOutTradeNo() +  "\"}");
//
//                keyValues.put("charset", "utf-8");
//
//                keyValues.put("method", "alipay.trade.app.pay");
//
//                keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");
//
//                keyValues.put("timestamp", "2016-07-29 16:55:53");
//
//                keyValues.put("version", "1.0");
//                lAlPayParameter.setSignInfo(obj.getString("signInfo"));
                PaymentRequest lPaymentRequest = new PaymentRequest(lAlPayParameter);
                lPaymentRequest.setPayType(PaymentConst.AL_PAY);
                ToPay.getInstance().toPayment(this, lPaymentRequest, this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPaymentSuccess(PaymentReponse result) {
        Log.e("PAY_GET", result.toString());
        Toast.makeText(this,result.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentFailure(String status) {
        Log.e("PAY_GET", String.valueOf(status));
        Toast.makeText(this,String.valueOf(status),Toast.LENGTH_LONG).show();
    }
}
