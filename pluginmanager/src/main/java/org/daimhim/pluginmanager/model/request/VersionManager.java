package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.response.ApkResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 项目名称：org.daimhim.pluginmanager.model.request
 * 项目版本：muster
 * 创建时间：2018/11/14 15:45  星期三
 * 创建人：Administrator
 * 修改时间：2018/11/14 15:45  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public interface VersionManager {
    @GET("apk/get/apk/list/")
    Observable<JavaResponse<ApkResponse>> getAllVersion(
            @Query("userId") String userId,
            @Query("pluginId") String pluginId
    );
    @POST("apk/register/app/")
    Observable<JavaResponse<Void>> registerVersion();
    @POST("apk/update/app/")
    Observable<JavaResponse<Void>> updateVersion();
}
