package org.daimhim.pluginmanager.model.request;

import org.daimhim.pluginmanager.model.bean.UserBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 项目名称：org.daimhim.pluginmanager.model.request
 * 项目版本：muster
 * 创建时间：2018/10/18 11:14  星期四
 * 创建人：Administrator
 * 修改时间：2018/10/18 11:14  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public interface User {
    @FormUrlEncoded
    @POST("user/registered/")
    Observable<JavaResponse> userRegistered(
            @Field("userName") String userName,
            @Field("passWord") String passWord);
    @FormUrlEncoded
    @POST("user/login/")
    Observable<JavaResponse<UserBean>> userLogin(
            @Field("userName") String userName,
            @Field("passWord") String passWord);
}
