package org.daimhim.pluginmanager.ui.version;


import org.daimhim.distance.RetrofitManager;
import org.daimhim.helpful.util.HLogUtil;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.request.VersionManager;
import org.daimhim.pluginmanager.model.response.ApkResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.base.BaseViewModel;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import timber.log.Timber;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.version
 * 项目版本：muster
 * 创建时间：2018/11/14 15:42  星期三
 * 创建人：Administrator
 * 修改时间：2018/11/14 15:42  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class VersionViewModel extends BaseViewModel {

    private VersionManager mVersionManager = RetrofitManager.getInstance().getRetrofit().create(VersionManager.class);

    public Observable<JavaResponse<ApkResponse>> getAllVersion(String pluginId){
        return mVersionManager.getAllVersion(UserHelp.getInstance().getUserId(),pluginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<JavaResponse<Void>> registerVersion(Map<String,String> pMaps){
        HashMap<String, String> pMap = new HashMap<>(pMaps);
        String lApkPath = pMap.get("apkPath");
        pMap.put("apkPath", pMap.get("appUrl"));
        pMap.put("appUrl",lApkPath);
        HashMap<String, RequestBody> lHashMap = new HashMap<>();

        Iterator<Map.Entry<String, String>> lIterator = pMap.entrySet().iterator();
        RequestBody lRequestBody = null;
        while (lIterator.hasNext()){
            Map.Entry<String, String> lNext = lIterator.next();
            Timber.i("getKey:%s getValue:%s",lNext.getKey(),lNext.getValue());
            if ("appLogo".equals(lNext.getKey()) || "apkPath".equals(lNext.getKey())){
                lRequestBody = MultipartBody.create(MediaType.parse("multipart/form-data"),new File(lNext.getValue()));
            }else {
                lRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),lNext.getValue());
            }
            lHashMap.put(lNext.getKey(),lRequestBody);
        }
        lHashMap.put("userId",RequestBody.create(MediaType.parse("multipart/form-data"),UserHelp.getInstance().getUserId()));
        return mVersionManager.registerVersion(lHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public void updateVersion(){

    }


}
