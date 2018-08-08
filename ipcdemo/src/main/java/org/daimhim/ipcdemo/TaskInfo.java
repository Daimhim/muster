package org.daimhim.ipcdemo;

import android.graphics.drawable.Drawable;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.07 10:50
 * 修改人：Daimhim
 * 修改时间：2018.08.07 10:50
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
class TaskInfo {
    private long MemorySize;
    private Drawable Icon;
    private String AppName;
    private String PackageName;
    private String processName;
    private boolean UserApp;

    @Override
    public String toString() {
        return "TaskInfo{" +
                "MemorySize=" + MemorySize +
                ", Icon=" + Icon +
                ", AppName='" + AppName + '\'' +
                ", PackageName='" + PackageName + '\'' +
                ", processName='" + processName + '\'' +
                ", UserApp=" + UserApp +
                '}';
    }

    public long getMemorySize() {
        return MemorySize;
    }

    public void setMemorySize(long pMemorySize) {
        MemorySize = pMemorySize;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public void setIcon(Drawable pIcon) {
        Icon = pIcon;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String pAppName) {
        AppName = pAppName;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String pPackageName) {
        PackageName = pPackageName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String pProcessName) {
        processName = pProcessName;
    }

    public boolean isUserApp() {
        return UserApp;
    }

    public void setUserApp(boolean pUserApp) {
        UserApp = pUserApp;
    }
}
