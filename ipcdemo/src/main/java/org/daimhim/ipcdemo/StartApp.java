package org.daimhim.ipcdemo;

import android.app.Application;

import java.util.List;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.07 15:29
 * 修改人：Daimhim
 * 修改时间：2018.08.07 15:29
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class StartApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessDetector.getInstance().init(this);
    }
}
