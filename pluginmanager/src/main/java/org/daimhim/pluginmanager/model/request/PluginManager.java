package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.bean.PluginBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PluginManager {

    @FormUrlEncoded
    @POST
    Observable<JavaResponse<PluginBean>> register_plugin(
            @Field("pluginName") String pluginName,
            @Field("packageName") String packageName,
            @Field("pluginDescription") String pluginDescription
    );

}
