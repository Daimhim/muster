package org.daimhim.pluginmanager.utils;

import com.google.gson.Gson;

/**
 * 项目名称：org.daimhim.pluginmanager.utils
 * 项目版本：muster
 * 创建时间：2018/10/19 10:26  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 10:26  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class GUs {
    private GUs(){
        init();
    }

    private void init() {
        mGson = new Gson();
    }

    public static GUs getInstance(){
        return GUs.SingletonHolder.sInstance;
    }
    private static class SingletonHolder {
        private static final GUs sInstance = new GUs();
    }

    private Gson mGson;

    public Gson getGson() {
        return mGson;
    }
}
