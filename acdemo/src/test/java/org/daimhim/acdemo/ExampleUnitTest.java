package org.daimhim.acdemo;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Before
    public void init(){
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN("http://v.juhe.cn/");
        lConfig.setCacheFile(System.getProperty("user.dir"));
        lConfig.setNetConnectedListener(new NetConnectedListener() {
            @Override
            public boolean isNetConnected() {
                return true;
            }
        });
        lConfig.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request proxylRequest;
                Request lRequest = chain.request();
                switch (lRequest.method()){
                    case "GET":
                        break;
                    case "POST":
                        break;
                    case "PUT":
                        break;
                }
                HttpUrl lUrl = lRequest.url();
                return chain.proceed(lRequest);
            }
        });
        RetrofitManager.getInstance().init(lConfig);
    }
    @Test
    public void addition_isCorrect() throws IOException {
        assertEquals(4, 2 + 2);
        TestRequest lTestRequest = RetrofitManager.getInstance().getRetrofit().create(TestRequest.class);
        lTestRequest.getjick().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable pDisposable) {

            }

            @Override
            public void onNext(String pS) {
                System.out.printf(pS);
            }

            @Override
            public void onError(Throwable pThrowable) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
    @After
    public void clear(){

    }
}