package org.daimhim.ipcdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug;
import android.text.format.Formatter;
import android.view.View;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    public static List<TaskInfo> getProcessInfo(Context pContext,boolean isAll){
        List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();

        //包管理器
        PackageManager packageManager = pContext.getPackageManager();
        //获取到进程管理器
        ActivityManager activityManager = (ActivityManager) pContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runprocessInfo : appProcesses) {
            TaskInfo taskInfo = new TaskInfo();
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
                    if (isAll) {
                        continue;
                    }
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



    public static List<TaskInfo> getTaskInfos(Context pContext,boolean isAll) {
        //包管理器
        PackageManager packageManager = pContext.getPackageManager();
        //获取到进程管理器
        ActivityManager activityManager = (ActivityManager) pContext.getSystemService(Context.ACTIVITY_SERVICE);

        List<AndroidAppProcess> processInfos = AndroidProcesses.getRunningAppProcesses();

        List<TaskInfo> taskinfos = new ArrayList<TaskInfo>();
        // 遍历运行的程序,并且获取其中的信息
        for (AndroidAppProcess processInfo : processInfos) {
            TaskInfo taskinfo = new TaskInfo();
            // 应用程序的包名
            String packname = processInfo.name;
            taskinfo.setPackageName(packname);
            // 湖区应用程序的内存 信息
            android.os.Debug.MemoryInfo[] memoryInfos = activityManager
                    .getProcessMemoryInfo(new int[] { processInfo.pid });
            long memsize = memoryInfos[0].getTotalPrivateDirty() * 1024L;
            taskinfo.setMemorySize(memsize);
            taskinfo.setPackageName(processInfo.getPackageName());
            try {
                // 获取应用程序信息
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(
                        packname, 0);
                Drawable icon = applicationInfo.loadIcon(packageManager);
                taskinfo.setIcon(icon);
                String name = applicationInfo.loadLabel(packageManager).toString();
                taskinfo.setAppName(name);

                if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    // 用户进程
                    taskinfo.setUserApp(true);
                    if (isAll) {
                        continue;
                    }
                } else {
                    // 系统进程
                    taskinfo.setUserApp(false);
                }
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // 系统内核进程 没有名称
                taskinfo.setAppName(packname);
                Drawable icon = pContext.getResources().getDrawable(
                        R.mipmap.ic_launcher);
                taskinfo.setIcon(icon);
            }
            if (taskinfo != null) {
                taskinfos.add(taskinfo);
                for (int i=0;i<taskinfos.size();i++) {
                    if (taskinfos.get(i).getPackageName().equals(packageManager.getNameForUid(processInfo.pid))){
                        taskinfos.remove(i);
                    }
                }
            }
        }
        return taskinfos;
    }


    public static long getTotalMemSize() {
        long size=0;
        File file = new File("/proc/meminfo");
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String memInfo = buffer.readLine();
            int startIndex = memInfo.indexOf(":");
            int endIndex = memInfo.indexOf("k");
            memInfo = memInfo.substring(startIndex + 1, endIndex).trim();
            size = Long.parseLong(memInfo);
            size *= 1024;
            buffer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public static long getAviableMemSize() {
        long size=0;
        File file = new File("/proc/meminfo");
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String memInfos="";
            String memInfo="";

            int i=0;
            while ((memInfos=buffer.readLine())!=null){
                i++;
                if (i==2){
                    memInfo = memInfos;
                }

            }
            int startIndex = memInfo.indexOf(":");
            int endIndex = memInfo.indexOf("k");
            memInfo = memInfo.substring(startIndex + 1, endIndex).trim();
            size = Long.parseLong(memInfo);
            size *= 1024;
            buffer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return size;
    }

    public static List<TaskInfo> getTaskInfoAll(Context pContext,boolean isAll){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getTaskInfos(pContext,isAll);
        }else {
            return getProcessInfo(pContext, isAll);
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
