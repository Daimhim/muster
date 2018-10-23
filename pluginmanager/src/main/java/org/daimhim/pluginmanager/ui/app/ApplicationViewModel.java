package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.request.Application;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.utils.CacheFileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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

    public LiveData<List<ApplicationBean>> getApplicationList(){
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
    public void upLoadApk(String url){
        mApplication.downLoad(url)
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody pResponseBody) {
                        CacheFileUtils lInstance = CacheFileUtils.getInstance();
//                        lInstance.getDiskCacheDir(CacheFileUtils.CACHE_FILE_DIR)
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
