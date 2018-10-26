package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModel;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.helpful.util.HImageUtil;
import org.daimhim.pluginmanager.StartApp;
import org.daimhim.pluginmanager.model.bean.AddAppMenuBean;
import org.daimhim.pluginmanager.model.request.Application;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.utils.CacheFileUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.app
 * 项目版本：muster
 * 创建时间：2018/10/26 10:55  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/26 10:55  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class EditAppViewModel extends ViewModel {
    private Application mApplication = RetrofitManager.getInstance().getRetrofit().create(Application.class);
    private String[] mInputMenu = {
            "appLogo", //0
            "appName", //1
            "appUrl",  //2
            "packageName", //3
            "versionName",  //4
            "versionCode", //5
            "minSdkVersion",  //6
            "targetSdkVersion", //7
    };

    public Observable<ArrayMap<String, String>> upLoadApk(String url) {
        return mApplication.downLoad(url)
                .map(pResponseBody -> {
                    CacheFileUtils lInstance = CacheFileUtils.getInstance();
                    return lInstance.saveFile(
                            pResponseBody.byteStream(),
                            pResponseBody.contentLength(),
                            lInstance.getDiskCacheDir(CacheFileUtils.CACHE_FILE_DIR).getAbsolutePath(),
                            lInstance.generateRandomFilename("apk")
                    );
                })
                .map(pFile -> {
                    ArrayMap<String, String> lArrayMap = new ArrayMap<>();
                    CacheFileUtils lInstance = CacheFileUtils.getInstance();
                    PackageManager lPackageManager = StartApp.getInstance().getPackageManager();
                    PackageInfo lPackageArchiveInfo = lPackageManager.getPackageArchiveInfo(pFile.getPath(), PackageManager.GET_ACTIVITIES);
                    if (lPackageArchiveInfo != null) {
                        ApplicationInfo appInfo = lPackageArchiveInfo.applicationInfo;
                        appInfo.sourceDir = pFile.getPath();
                        appInfo.publicSourceDir = pFile.getPath();
                        Drawable lDrawable = appInfo.loadIcon(lPackageManager);
                        Uri lPng = lInstance.saveBitmap(HImageUtil.drawableToBitmap(lDrawable),
                                lInstance.getDiskCacheDir(CacheFileUtils.CACHE_IMAGE_DIR).getAbsolutePath(),
                                lInstance.generateRandomFilename("png"));
                        lArrayMap.put(mInputMenu[0], lPng.getPath());
                        lArrayMap.put(mInputMenu[1], lPackageManager.getApplicationLabel(appInfo).toString());
                        lArrayMap.put(mInputMenu[2], url);
                        lArrayMap.put(mInputMenu[3], appInfo.packageName);
                        lArrayMap.put(mInputMenu[4], lPackageArchiveInfo.versionName);
                        if (Build.VERSION.SDK_INT >= 28) {
                            lArrayMap.put(mInputMenu[5], String.valueOf(lPackageArchiveInfo.getLongVersionCode()));
                        } else {
                            lArrayMap.put(mInputMenu[5], String.valueOf(lPackageArchiveInfo.versionCode));
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            lArrayMap.put(mInputMenu[6], String.valueOf(appInfo.minSdkVersion));
                        }
                        lArrayMap.put(mInputMenu[7], String.valueOf(appInfo.targetSdkVersion));
                    }
                    return lArrayMap;
                });
    }

    public Observable<List<AddAppMenuBean>> getInputMenu() {
        List<AddAppMenuBean> lPairs = new ArrayList<>();
        AddAppMenuBean lBean = null;
        for (int i = 0; i < mInputMenu.length; i++) {
            lBean = new AddAppMenuBean();
            lBean.setKey(mInputMenu[i]);
            lPairs.add(lBean);
        }
        return Observable.just(lPairs);
    }

    public Observable<JavaResponse<Void>> registeredApp(Map<String, String> pPairs) {
        return mApplication.appRegistered(pPairs);
    }
}
