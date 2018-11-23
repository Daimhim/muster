package org.daimhim.pagingdemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import org.daimhim.distance.RetrofitManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018.08.31 10:00  星期五
 * 创建人：Daimhim
 * 修改时间：2018.08.31 10:00  星期五
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class MainViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<PagedList<JokeBean>> mCurrentUser;
    private Joke mJoke;

    public MutableLiveData<PagedList<JokeBean>> getCurrentName() {
        if (mCurrentUser == null) {
            mCurrentUser = new MutableLiveData<>();
            loadData();
        }
        return mCurrentUser;
    }

    public PagedList<JokeBean> loadData() {
        if (mJoke == null) {
            mJoke = RetrofitManager.getInstance().getRetrofit().create(Joke.class);
        }
        DataSource<Integer, JokeBean> lPageKeyedDataSource = new PageKeyedDataSource<Integer, JokeBean>() {
            @Override
            public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, JokeBean> callback) {
                Timber.i("loadInitial:%s",params.requestedLoadSize);
                Timber.i("loadInitial:%s",params);
                ArrayList<JokeBean> lJokeBeans = new ArrayList<>();
                JokeBean lBean  = null;
                for (int i = 0; i < 20; i++) {
                    lBean = new JokeBean();
                    lBean.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA).format(new Date(System.currentTimeMillis())));
                    lBean.setHashId(""+i);
                    lBean.setContent("错误: 不兼容的类型: long无法转换为String:"+i);
                    lJokeBeans.add(lBean);
                }
                callback.onResult(lJokeBeans,0,1);
            }

            @Override
            public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, JokeBean> callback) {
                Timber.i("loadBefore:%s",params.key);

            }

            @Override
            public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, JokeBean> callback) {
                Timber.i("loadAfter key:%s",params.key);
                Timber.i("loadAfter requestedLoadSize:%s",params.requestedLoadSize);
                ArrayList<JokeBean> lJokeBeans = new ArrayList<>();
                JokeBean lBean  = null;
                for (int i = 0; i < params.requestedLoadSize; i++) {
                    lBean = new JokeBean();
                    lBean.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA).format(new Date(System.currentTimeMillis())));
                    lBean.setHashId(""+(i*params.requestedLoadSize));
                    lBean.setContent("错误: 不兼容的类型: long无法转换为String:"+(i*params.requestedLoadSize));
                    lJokeBeans.add(lBean);
                }
                callback.onResult(lJokeBeans,params.key);
            }
        };
//        new ScheduledThreadPoolExecutor()
        PagedList.Builder<Integer, JokeBean> lIntegerJokeBeanBuilder = new PagedList.Builder<>(lPageKeyedDataSource,
                10)
                .setInitialKey(0)
                .setNotifyExecutor(new Executor() {
                    @MainThread
                    @Override
                    public void execute(Runnable command) {
                        command.run();
                    }
                })
                .setFetchExecutor(new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        new Thread(command).start();
                    }
                });
        return lIntegerJokeBeanBuilder.build();
    }

}
