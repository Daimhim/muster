package org.daimhim.pluginmanager.ui.version;


import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.request.VersionManager;
import org.daimhim.pluginmanager.model.response.ApkResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.base.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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




}
