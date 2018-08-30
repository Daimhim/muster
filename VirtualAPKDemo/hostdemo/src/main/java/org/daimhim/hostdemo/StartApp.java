package org.daimhim.hostdemo;

import android.app.Application;

import com.didi.virtualapk.PluginManager;

/**
 * 项目名称：org.daimhim.hostdemo
 * 项目版本：VirtualAPKDemo
 * 创建时间：2018.08.28 16:06  星期二
 * 创建人：Daimhim
 * 修改时间：2018.08.28 16:06  星期二
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class StartApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager.getInstance(this).init();
    }

}
