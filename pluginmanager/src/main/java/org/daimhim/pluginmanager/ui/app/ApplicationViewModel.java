package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModel;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.request.Application;
import org.daimhim.pluginmanager.model.response.ApplicationResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observable;


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
    private Application mApplication = RetrofitManager.getInstance().getRetrofit().create(Application.class);


    public Observable<JavaResponse<ApplicationResponse>> loadApplicationList() {
        return mApplication.getAappList(UserHelp.getInstance().getUserId());
    }

}
