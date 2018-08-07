package org.daimhim.ipcdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.text.format.Formatter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.07 10:43
 * 修改人：Daimhim
 * 修改时间：2018.08.07 10:43
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class IPCHelp {

    public static List<TaskInfo> getProcessInfo(Context pContext){
        List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();

        //包管理器
        PackageManager packageManager = pContext.getPackageManager();
        //获取到进程管理器
        ActivityManager activityManager = (ActivityManager) pContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runprocessInfo : appProcesses) {
            TaskInfo taskInfo = new TaskInfo();

            // List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);

            //installedPackages.
            try {

                //获取到当前进程的pid      // 用户ID 类似于Linux的权限不同，ID也就不同 比如 root等
                int pid = runprocessInfo.pid;

                int[] myMempid = new int[]{pid};

                //获取到内存的基本信息
                Debug.MemoryInfo[] memoryInfo = activityManager.getProcessMemoryInfo(myMempid);
                //Debug.MemoryInfo memoryInfo = activityManager.ge;
                //getTotalPrivateDirty()返回的值单位是KB，所以我们要换算成MB，也就是乘以1024
                int totalPrivateDirty = memoryInfo[0].getTotalPrivateDirty() * 1024;
                /**
                 * 这个里面一共只有一个数据
                 */
                //获取到总共弄脏了多少内存(也就是当前应用程序占用了多少内存)
                //int totalPrivateDirty = memoryInfo[0].getTotalPrivateDirty() * 1024;

                Formatter.formatFileSize(pContext,totalPrivateDirty);

                //获取到进程的名字
                String processName = runprocessInfo.processName;
                PackageInfo packageInfo = packageManager.getPackageInfo(processName, 0);

                //获取到应用的图标
                Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);

                //获取到app的名字
                String appName = packageInfo.applicationInfo.loadLabel(packageManager).toString();


                taskInfo.setMemorySize(totalPrivateDirty);
                taskInfo.setIcon(icon);
                taskInfo.setAppName(appName);
                taskInfo.setPackageName(processName);

                //获取到当前应用的额标记
                int flags = packageInfo.applicationInfo.flags;

                if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    //如果是true，就是系统应用
                    taskInfo.setUserApp(false);
                } else {
                    //如果是false，就是用户进程
                    taskInfo.setUserApp(true);
                }

                taskInfos.add(taskInfo);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                //有的系统应用核心库里面，有些进程没有图标，要给一个默认的
                taskInfo.setAppName("");
                taskInfo.setIcon(pContext.getResources().getDrawable(R.mipmap.ic_launcher));
            }

        }
        return taskInfos;
    }
}
