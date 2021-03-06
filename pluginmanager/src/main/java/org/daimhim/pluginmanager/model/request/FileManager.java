package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.bean.FileBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 项目名称：org.daimhim.pluginmanager.model.request
 * 项目版本：muster
 * 创建时间：2018/10/30 15:17  星期二
 * 创建人：Administrator
 * 修改时间：2018/10/30 15:17  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public interface FileManager {
    @Multipart
    @POST("upLoadFile/")
    Observable<JavaResponse<FileBean>> upLoadFile(
            @Part("userId") RequestBody userId, @Part MultipartBody.Part file
    );

    @GET("getFile/")
    @Deprecated
    Observable<JavaResponse<ResponseBody>> getFile(
            @Field("userId") String userId,
            @Field("fileId") String fileId
    );
}
