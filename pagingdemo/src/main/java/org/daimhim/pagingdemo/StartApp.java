package org.daimhim.pagingdemo;

import android.app.Application;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;

import timber.log.Timber;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018/11/23 11:58  星期五
 * 创建人：Administrator
 * 修改时间：2018/11/23 11:58  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class StartApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN("http://www.baidu.com");
        lConfig.setCallAsync(true);
        lConfig.setCacheFile(getCacheDir().getAbsolutePath());
        lConfig.setNetConnectedListener(new NetConnectedListener() {
            @Override
            public boolean isNetConnected() {
                return NetWorkUtils.isNetworkConnected(getApplicationContext());
            }
        });
        RetrofitManager.getInstance().init(lConfig);
    }
}
