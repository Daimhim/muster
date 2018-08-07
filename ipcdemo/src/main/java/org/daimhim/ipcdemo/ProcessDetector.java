package org.daimhim.ipcdemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.07 11:07
 * 修改人：Daimhim
 * 修改时间：2018.08.07 11:07
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class ProcessDetector {
    private static Context mContext;
    private static ProcessDetectorAdapter mListView;

    private ProcessDetector() {

    }

    public static ProcessDetector getInstance() {
        return SingletonHolder.sInstance;
    }

    public void init(Context pContext){
        mContext = pContext;
        WindowManager lSystemService = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        final View lInflate = LayoutInflater.from(mContext).inflate(R.layout.list_process_detector, null, false);
        mListView = new ProcessDetectorAdapter();
        ListView lViewById = (ListView) lInflate.findViewById(R.id.lv_list);
        lViewById.setAdapter(mListView);
        lViewById.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                getInstance().onRefresh(IPCHelp.getProcessInfo(mContext));
                return false;
            }
        });
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 24) { /*android7.0不能用TYPE_TOAST*/
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else { /*以下代码块使得android6.0之后的用户不必再去手动开启悬浮窗权限*/
            String packname = mContext.getPackageName();
            PackageManager pm = mContext.getPackageManager();
            boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.SYSTEM_ALERT_WINDOW", packname));
            if (permission) {
                wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
        }
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.END | Gravity.TOP;

        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        lSystemService.getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;
        //以屏幕左上角为原点，设置x、y初始值，相对于gravity
        wmParams.x = screenWidth;
        wmParams.y = screenHeight;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lSystemService.addView(lInflate, wmParams);
    }

    private static class SingletonHolder {
        private static final ProcessDetector sInstance = new ProcessDetector();
    }

    public void onRefresh(List<TaskInfo> pList) {
        mListView.onRefresh(pList);
    }


}
