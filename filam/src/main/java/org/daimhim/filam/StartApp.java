package org.daimhim.filam;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * 项目名称：org.daimhim.filam
 * 项目版本：muster
 * 创建时间：2018/11/22 10:54  星期四
 * 创建人：Administrator
 * 修改时间：2018/11/22 10:54  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class StartApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
