package org.daimhim.pluginmanager;

import android.app.Application;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/17 17:34  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/17 17:34  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class StartApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN("http:");
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
