package org.daimhim.acdemo;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Before
    public void init() {
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN("http://test.meyki.net/zsmapi/");
        lConfig.setCacheFile(System.getProperty("user.dir"));
        lConfig.setNetConnectedListener(new NetConnectedListener() {
            @Override
            public boolean isNetConnected() {
                return true;
            }
        });
        lConfig.addInterceptor(new MeykiProxy());
        RetrofitManager.getInstance().init(lConfig);
    }

    @Test
    public void addition_isCorrect() throws IOException {
        assertEquals(4, 2 + 2);
        TestRequest lTestRequest = RetrofitManager.getInstance().getRetrofit().create(TestRequest.class);
        lTestRequest.shopSaleTicket("108.890146", "34.21288", "001")
                .subscribe(new Observer<String>() {
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
    public void clear() {

    }

}