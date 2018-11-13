package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.model.response.PluginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PluginManager {

    @FormUrlEncoded
    @POST("apk/register/plugin/")
    Observable<JavaResponse<Void>> uploadPlugin(
            @Field("appId") String appId,
            @Field("pluginName") String pluginName,
            @Field("packageName") String packageName,
            @Field("pluginDescription") String pluginDescription
    );
    @GET("apk/get/plugin/list/")
    Observable<JavaResponse<PluginResponse>> getPluginList(
            @Query("userId") String userId,
            @Query("appId") String appId
    );


}
