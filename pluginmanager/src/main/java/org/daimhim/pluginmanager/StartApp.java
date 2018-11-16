package org.daimhim.pluginmanager;

import android.app.Application;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.daimhim.helpful.util.HAppUtil;
import org.daimhim.helpful.util.HFileUtil;
import org.daimhim.helpful.util.HImageUtil;
import org.daimhim.pluginmanager.utils.CacheFileUtils;
import org.daimhim.pluginmanager.utils.NetWorkUtils;

import timber.log.Timber;


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
    private static StartApp app;

    public static StartApp getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Timber.plant(new Timber.DebugTree());
        CacheFileUtils.getInstance().initCacheFile(this);
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN(BuildConfig.BASE_URL);
        lConfig.setCallAsync(true);
        lConfig.setCacheFile(getCacheDir().getAbsolutePath());
        lConfig.setNetConnectedListener(new NetConnectedListener() {
            @Override
            public boolean isNetConnected() {
                return NetWorkUtils.isNetworkConnected(getApplicationContext());
            }
        });
        RetrofitManager.getInstance().init(lConfig);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HFileUtil.initFileDir(getBaseContext());
                CacheFileUtils.getInstance().initCacheFile(getBaseContext());
            }
        }).start();
    }
}
