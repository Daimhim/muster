package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.helpful.util.HLogUtil;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.request.Application;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.utils.CacheFileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/17 17:37  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/17 17:37  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ApplicationViewModel extends ViewModel {
    private MutableLiveData<List<ApplicationBean>> mApplicationBeanMutableLiveData;
    private Application mApplication = RetrofitManager.getInstance().getRetrofit().create(Application.class);

    public LiveData<List<ApplicationBean>> getApplicationList() {
        if (null == mApplicationBeanMutableLiveData) {
            mApplicationBeanMutableLiveData = new MutableLiveData<>();
            loadApplicationList();

        }
        return mApplicationBeanMutableLiveData;
    }

    public void loadApplicationList() {
        mApplication.getAappList(UserHelp.getInstance().getUserId())
                .subscribe(new Observer<JavaResponse<ApplicationBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JavaResponse<ApplicationBean> pApplicationBeanJavaResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public Observable<JavaResponse<Uri>> upLoadApk(String url) {
        return mApplication.downLoad(url)
                .map(new Function<ResponseBody, Uri>() {
                    @Override
                    public Uri apply(ResponseBody pResponseBody) throws Exception {
                        CacheFileUtils lInstance = CacheFileUtils.getInstance();
                        File lApk = lInstance.saveFile(pResponseBody.byteStream(), pResponseBody.contentLength(),
                                lInstance.getDiskCacheDir(CacheFileUtils.CACHE_FILE_DIR).getAbsolutePath(), lInstance.generateRandomFilename("apk"));
                        return Uri.fromFile(lApk);
                    }
                })
                .map(new Function<Uri, JavaResponse<Uri>>() {
                    @Override
                    public JavaResponse<Uri> apply(Uri pUri) throws Exception {
                        JavaResponse<Uri> lUriJavaResponse = new JavaResponse<>();
                        lUriJavaResponse.setResult(pUri);
                        return lUriJavaResponse;
                    }
                });
    }

    public Observable<Map<String,String>> getInputMenu(){
        Map<String,String> lPairList = new LinkedHashMap<>();
        Class<ApplicationBean> lApplicationBeanClass = ApplicationBean.class;
        Field[] lFields = lApplicationBeanClass.getDeclaredFields();
        for (int i = lFields.length-1; i >= 0; i--) {
            if (!TextUtils.equals(lFields[i].getName(),"app_id")) {
                lPairList.put(lFields[i].getName(), "");
            }
        }
        return Observable.just(lPairList);
    }

    public Observable<JavaResponse<Void>> registeredApp(Map<String,String> pPairs){
        return mApplication.appRegistered(pPairs);
    }
}
