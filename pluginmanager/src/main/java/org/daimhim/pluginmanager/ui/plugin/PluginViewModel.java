package org.daimhim.pluginmanager.ui.plugin;

import android.arch.lifecycle.ViewModel;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.request.PluginManager;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.model.response.PluginResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PluginViewModel extends ViewModel {


    private PluginManager mPluginManager = RetrofitManager.getInstance().getRetrofit().create(PluginManager.class);

    public Observable<JavaResponse<Void>> uploadPlugin(
            String pluginName,
            String packageName,
            String pluginDescription) {
        return mPluginManager.uploadPlugin(pluginName, packageName, pluginDescription)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<JavaResponse<PluginResponse>> getPluginList(
            String pluginId) {
        return mPluginManager.getPluginList(UserHelp.getInstance().getUserId(), pluginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
