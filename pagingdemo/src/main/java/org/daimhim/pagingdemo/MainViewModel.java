package org.daimhim.pagingdemo;

import android.annotation.SuppressLint;
import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.util.Function;
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
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;

import org.daimhim.distance.RetrofitManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    private String mKey = "6a7f40ff902220aead73f5f746d423f1";
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
            public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, JokeBean> callback) {
                Timber.i("loadInitial:%s", params.requestedLoadSize);
                mJoke.jokeContent(1, params.requestedLoadSize, mKey)
                        .subscribe(new CallObserver<BaseResponse<JokeResponse>>() {
                            @Override
                            public void onNext(BaseResponse<JokeResponse> pJokeResponseBaseResponse) {
                                callback.onResult(pJokeResponseBaseResponse.getResult().getData(), 1, 2);
                            }
                        });
            }

            @Override
            public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, JokeBean> callback) {
                Timber.i("loadBefore key:%s", params.key);
//                if (params.key == 1){
//                    callback.onResult(new ArrayList<JokeBean>(),params.key);
//                    return;
//                }
//                mJoke.jokeContent(params.key, params.requestedLoadSize, mKey)
//                        .subscribe(new CallObserver<BaseResponse<JokeResponse>>() {
//                            @Override
//                            public void onNext(BaseResponse<JokeResponse> pJokeResponseBaseResponse) {
//                                callback.onResult(pJokeResponseBaseResponse.getResult().getData(), (params.key - 1));
//                            }
//                        });
            }

            @Override
            public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, JokeBean> callback) {
                Timber.i("loadAfter key:%s", params.key);
                if (params.key == 10){
                    callback.onResult(Collections.<JokeBean>emptyList(),params.key);
                    return;
                }
                mJoke.jokeContent(params.key, params.requestedLoadSize, mKey)
                        .subscribe(new CallObserver<BaseResponse<JokeResponse>>() {
                            @Override
                            public void onNext(BaseResponse<JokeResponse> pJokeResponseBaseResponse) {
                                callback.onResult(pJokeResponseBaseResponse.getResult().getData(), params.key + 1);
                            }
                        });
            }
        };

        @SuppressLint("RestrictedApi") PagedList.Builder<Integer, JokeBean> lIntegerJokeBeanBuilder = new PagedList.Builder<>(lPageKeyedDataSource,
                8)
                .setInitialKey(1)
                .setBoundaryCallback(new PagedList.BoundaryCallback<JokeBean>() {
                    @Override
                    public void onItemAtEndLoaded(@NonNull JokeBean itemAtEnd) {
                        super.onItemAtEndLoaded(itemAtEnd);

                        Timber.i("onItemAtEndLoaded:%s",itemAtEnd.toString());
                    }

                    @Override
                    public void onItemAtFrontLoaded(@NonNull JokeBean itemAtFront) {
                        super.onItemAtFrontLoaded(itemAtFront);
                        Timber.i("onItemAtFrontLoaded:%s",itemAtFront.toString());
                    }

                    @Override
                    public void onZeroItemsLoaded() {
                        super.onZeroItemsLoaded();
                        Timber.i("onZeroItemsLoaded");
                    }
                })
                .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor())
                .setFetchExecutor(ArchTaskExecutor.getIOThreadExecutor());
        return lIntegerJokeBeanBuilder.build();
    }

}
