package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModel;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.helpful.util.HImageUtil;
import org.daimhim.pluginmanager.StartApp;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.AddAppMenuBean;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.bean.FileBean;
import org.daimhim.pluginmanager.model.request.Application;
import org.daimhim.pluginmanager.model.request.FileManager;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.utils.CacheFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    private FileManager mFileManager = RetrofitManager.getInstance().getRetrofit().create(FileManager.class);
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

    public Observable<List<AddAppMenuBean>> getInputMenu(ApplicationBean pApplicationBean) {
        List<AddAppMenuBean> lPairs = null;
        if (null!=pApplicationBean){
            lPairs = partConversion(pApplicationBean);
        }else {
            lPairs = new ArrayList<>();
            AddAppMenuBean lBean = null;
            for (int i = 0; i < mInputMenu.length; i++) {
                lBean = new AddAppMenuBean();
                lBean.setKey(mInputMenu[i]);
                lPairs.add(lBean);
            }
        }
        return Observable.just(lPairs);
    }

    public Observable<ArrayMap<String, String>> analyzeLocal(File file) {
        return Observable.just(file)
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
                        lArrayMap.put(mInputMenu[2], pFile.getPath());
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

    public Observable<JavaResponse<Void>> editApp(ApplicationBean pApplicationBean) {
        Map<String, String> lStringMap = new HashMap<>();
        lStringMap.put("userId",UserHelp.getInstance().getUserId());
        lStringMap.put("appLogo",pApplicationBean.getApp_logo());
        lStringMap.put("appName",pApplicationBean.getApp_name());
        lStringMap.put("appUrl",pApplicationBean.getApp_url());
        lStringMap.put("packageName",pApplicationBean.getPackage_name());
        lStringMap.put("versionName",pApplicationBean.getVersion_name());
        lStringMap.put("versionCode",pApplicationBean.getVersion_code());
        lStringMap.put("minSdkVersion",pApplicationBean.getMin_sdk_version());
        lStringMap.put("targetSdkVersion",pApplicationBean.getTarget_sdk_version());

        if (!pApplicationBean.getApp_logo().startsWith("http")){

            File lFile = new File(pApplicationBean.getApp_logo());
            RequestBody lRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), lFile);
            MultipartBody.Part lFile1 = MultipartBody.Part.createFormData("file", lFile.getName(), lRequestBody);

            lRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), UserHelp.getInstance().getUserId());

            return mFileManager.upLoadFile(lRequestBody,lFile1)
                    .flatMap((Function<JavaResponse<FileBean>, ObservableSource<JavaResponse<Void>>>) pFileBeanJavaResponse -> {
                        if (pFileBeanJavaResponse.getResult() == null){
                            JavaResponse<Void> lVoidJavaResponse = new JavaResponse<>();
                            lVoidJavaResponse.setError_code(pFileBeanJavaResponse.getError_code());
                            lVoidJavaResponse.setError_msg(pFileBeanJavaResponse.getError_msg());
                            return Observable.just(lVoidJavaResponse);
                        }
                        lStringMap.put("appLogo",pFileBeanJavaResponse.getResult().getFile_id());
                        if (TextUtils.isEmpty(pApplicationBean.getApp_id())){
                            return mApplication.appRegistered(lStringMap);
                        }else {
                            lStringMap.put("appId",pApplicationBean.getApp_id());
                            return mApplication.appUpdate(lStringMap);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        if (TextUtils.isEmpty(pApplicationBean.getApp_id())){
            return mApplication.appRegistered(lStringMap);
        }else {
            lStringMap.put("appId",pApplicationBean.getApp_id());
            return mApplication.appUpdate(lStringMap);
        }

    }

    public <T> T  partConversion(Object object){
        if (object instanceof ApplicationBean){
            ApplicationBean lObject = (ApplicationBean) object;
            List<AddAppMenuBean> lBeanList = new ArrayList<>();
            lBeanList.add(new AddAppMenuBean(mInputMenu[0],lObject.getApp_logo()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[1],lObject.getApp_name()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[2],lObject.getApp_url()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[3],lObject.getPackage_name()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[4],lObject.getVersion_name()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[5],lObject.getVersion_code()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[6],lObject.getMin_sdk_version()));
            lBeanList.add(new AddAppMenuBean(mInputMenu[7],lObject.getTarget_sdk_version()));
            return (T) lBeanList;
        }else if (object instanceof List){
            List<AddAppMenuBean> lObject = (List<AddAppMenuBean>) object;
            ApplicationBean lApplicationBean = new ApplicationBean();
            lApplicationBean.setApp_logo(lObject.get(0).getVaue());
            lApplicationBean.setApp_name(lObject.get(1).getVaue());
            lApplicationBean.setApp_url(lObject.get(2).getVaue());
            lApplicationBean.setPackage_name(lObject.get(3).getVaue());
            lApplicationBean.setVersion_name(lObject.get(4).getVaue());
            lApplicationBean.setVersion_code(lObject.get(5).getVaue());
            lApplicationBean.setMin_sdk_version(lObject.get(6).getVaue());
            lApplicationBean.setTarget_sdk_version(lObject.get(7).getVaue());
            return (T) lApplicationBean;
        }
        return null;
    }
}
