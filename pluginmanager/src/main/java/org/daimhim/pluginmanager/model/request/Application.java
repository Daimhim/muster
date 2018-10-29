package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.ApplicationResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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
    /**
     * @Field("userId") String userId,
     * @Field("appName") String appName,
     * @Field("appUrl") String appUrl,
     * @Field("appLogo") String appLogo,
     * @Field("packageName") String packageName
     */
    @FormUrlEncoded
    @POST("apk/register/app/")
    Observable<JavaResponse<Void>> appRegistered(
            @FieldMap Map<String,String> pMap
    );
    @FormUrlEncoded
    @POST("apk/update/app/")
    Observable<JavaResponse<Void>> appUpdate(
            @FieldMap Map<String,String> pMap
    );

    @POST("apk/delete/app/")
    void appDelete(
            @Field("userId") String userId,
            @Field("appId") String appId
    );

    @GET("apk/get/app/list/")
    Observable<JavaResponse<ApplicationResponse>> getAappList(
            @Query("userId") String userId
    );

    @GET("apk/get/app")
    void getApp(
            @Path("userId") String userId,
            @Path("appName") String appName,
            @Path("appUrl") String appUrl,
            @Path("appLogo") String appLogo,
            @Path("packageName") String packageName);
    @GET
    Observable<ResponseBody> downLoad(@Url String url);
}
