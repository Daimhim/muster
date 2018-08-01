package net.sourceforge.simcpux;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PaymentCallback {

    /**
     * WxPay
     */
    private Button mBtWxPay;
    /**
     * AlPay
     */
    private Button mBtAlPay;

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "";
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "";

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
                lAlPayParameter.setApp_id(APPID);
                lAlPayParameter.setBiz_content("{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"" + getOutTradeNo() +  "\"}");
                lAlPayParameter.setCharset("utf-8");
                lAlPayParameter.setMethod("alipay.trade.app.pay");
                lAlPayParameter.setSign_type(TextUtils.isEmpty(RSA2_PRIVATE)?"RSA":"RSA2");
                lAlPayParameter.setTimestamp("2016-07-29 16:55:53");
                lAlPayParameter.setVersion("1.0");
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

    /**
     * 要求外部订单号必须唯一。
     * @return
     */
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    private long mLong;
    @Override
    public void onBackPressed() {
        long lL = System.currentTimeMillis();
        Log.d("onBackPressed",String.format("onBackPressed:lL:%s mLong:%s lL-mLong:%s",lL,mLong,lL-mLong));
        if (mLong == 0){
            mLong = lL;
            Toast.makeText(this,"再按一次退回桌面",Toast.LENGTH_SHORT).show();
        }else if (lL - mLong < 1000) {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            mLong = 0;
        } else {
            mLong = lL;
            Toast.makeText(this,"再按一次退回桌面",Toast.LENGTH_SHORT).show();
        }
    }
}
