package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.response.ApkResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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
    @Multipart
    @POST("apk/upload/apk/")
    Observable<JavaResponse<Void>> registerVersion(@PartMap Map<String, RequestBody> args,@Part List<MultipartBody.Part> pPartList);

    @POST("apk/update/app/")
    Observable<JavaResponse<Void>> updateVersion();
}
