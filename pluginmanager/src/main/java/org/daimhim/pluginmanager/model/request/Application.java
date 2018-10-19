package org.daimhim.pluginmanager.model.request;

import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * 项目名称：org.daimhim.pluginmanager.model.request
 * 项目版本：muster
 * 创建时间：2018/10/18 16:42  星期四
 * 创建人：Administrator
 * 修改时间：2018/10/18 16:42  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public interface Application {
    @POST("apk/register/app/")
    void appRegistered(
            @Field("userId") String userId,
            @Field("appName") String appName,
            @Field("appUrl") String appUrl,
            @Field("appLogo") String appLogo,
            @Field("packageName") String packageName
    );

    @POST("apk/update/app/")
    void appUpdate(
            @Field("userId") String userId,
            @Field("appId") String appId,
            @Field("appName") String appName,
            @Field("appUrl") String appUrl,
            @Field("appLogo") String appLogo,
            @Field("packageName") String packageName
    );

    @POST("apk/delete/app/")
    void appDelete(
            @Field("userId") String userId,
            @Field("appId") String appId
    );

    @POST("apk/get/app/list/")
    void getAappList(
            @Field("userId") String userId
    );

    @POST("apk/get/app")
    void getApp(
            @Field("userId") String userId,
            @Field("appName") String appName,
            @Field("appUrl") String appUrl,
            @Field("appLogo") String appLogo,
            @Field("packageName") String packageName);
}
