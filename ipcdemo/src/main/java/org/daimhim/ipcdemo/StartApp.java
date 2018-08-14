package org.daimhim.ipcdemo;

import android.app.Application;
import android.util.Log;

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
    private static StartApp startApp;
    public static StartApp getInstance() {
        return startApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        startApp = this;
        Log.d("StartAppStartApp",IPCHelp.getCurProcessName(this));
        if ("org.daimhim.ipcdemo".equals(IPCHelp.getCurProcessName(this))){
            ProcessDetector.getInstance().init(this);
        }else if ("org.daimhim.ipcdemo:remote".equals(IPCHelp.getCurProcessName(this))){

        }else if ("org.daimhim.ipcdemo.remote".equals(IPCHelp.getCurProcessName(this))){

        }
    }
}
