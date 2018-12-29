package org.daimhim.acdemo;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 项目名称：org.daimhim.acdemo
 * 项目版本：muster
 * 创建时间：2018/12/3 13:58  星期一
 * 创建人：Administrator
 * 修改时间：2018/12/3 13:58  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class MeyKiPostTest {
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
        lConfig.setCallAsync(false);
        RetrofitManager.getInstance().init(lConfig);
    }

    @Test
    public void test() {
        TestRequest lTestRequest = RetrofitManager.getInstance().getRetrofit().create(TestRequest.class);

        File lFile = new File("E:\\android_private\\muster\\pluginmanager\\src\\main\\res\\drawable-xhdpi\\pass_t_pm.png");
        RequestBody lRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), lFile);
        MultipartBody.Part lFile1 = MultipartBody.Part.createFormData("file", lFile.getName(), lRequestBody);

        lTestRequest.uploadImage(lFile1)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String pS) {
                        System.out.println(pS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
