package org.daimhim.pagingdemo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018/11/23 11:56  星期五
 * 创建人：Administrator
 * 修改时间：2018/11/23 11:56  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public interface Joke {
    @GET("http://v.juhe.cn/joke/content/text.php")
    Observable<BaseResponse<JokeResponse>> jokeContent(@Query("page") int page,
                           @Query("pagesize") int pagesize,
                           @Query("key") String key
    );
}
