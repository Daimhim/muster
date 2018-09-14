package org.daimhim.distance;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private Config mConfig;

    private static RetrofitManager mRetrofitManage;
    private Retrofit mRetrofit;

    private RetrofitManager() {

    }

    public void init(Config pConfig) {
        mConfig = pConfig;
        getRetrofit();
    }

    public static RetrofitManager getInstance() {
        if (mRetrofitManage == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManage == null) {
                    mRetrofitManage = new RetrofitManager();
                }
            }
        }
        return mRetrofitManage;
    }

    /**
     * 获取retrofit对象
     */
    public Retrofit getRetrofit() {
        if (null == mRetrofit) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //缓存
            File cacheFile = new File(mConfig.cacheFile, "cache");
            //100Mb
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
            //创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder lBuilder = new OkHttpClient.Builder()
                    //读取超时
                    .readTimeout(mConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    //连接超时
                    .connectTimeout(mConfig.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    //写出超时
                    .writeTimeout(mConfig.WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    //云拦截缓存
                    .addInterceptor(mRewriteCacheControlInterceptor)
                    .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                    .cache(cache)
                    .addInterceptor(logInterceptor);
            for (int i = 0; i < mConfig.mInterceptor.size(); i++) {
                lBuilder.addInterceptor(mConfig.mInterceptor.get(i));
            }

            mRetrofit = new Retrofit.Builder()
                    .client(lBuilder.build())
                    .baseUrl(mConfig.BASE_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            //是网络连接
            if (!mConfig.mNetConnectedListener.isNetConnected()) {
                request = request.newBuilder()
                        .cacheControl("".equals(cacheControl) ? CacheControl
                                .FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (mConfig.mNetConnectedListener.isNetConnected()) {
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    public static class Config {
        private String cacheFile;
        private String BASE_DOMAIN;
        //读超时长，单位：毫秒
        private int READ_TIME_OUT = 3000;
        //连接时长，单位：毫秒
        private int CONNECT_TIME_OUT = 3000;
        //写出时长，单位：毫秒
        private int WRITE_TIME_OUT = 3000;
        private NetConnectedListener mNetConnectedListener;
        private ArrayList<Interceptor> mInterceptor;

        public Config() {
            mInterceptor = new ArrayList<>();
        }

        public void setCacheFile(String pCacheFile) {
            cacheFile = pCacheFile;
        }

        public void setBASE_DOMAIN(String pBASE_DOMAIN) {
            BASE_DOMAIN = pBASE_DOMAIN;
        }

        public void setREAD_TIME_OUT(int pREAD_TIME_OUT) {
            READ_TIME_OUT = pREAD_TIME_OUT;
        }

        public void setCONNECT_TIME_OUT(int pCONNECT_TIME_OUT) {
            CONNECT_TIME_OUT = pCONNECT_TIME_OUT;
        }

        public void setWRITE_TIME_OUT(int pWRITE_TIME_OUT) {
            WRITE_TIME_OUT = pWRITE_TIME_OUT;
        }

        public void setNetConnectedListener(NetConnectedListener pNetConnectedListener) {
            mNetConnectedListener = pNetConnectedListener;
        }

        public void addInterceptor(Interceptor pInterceptor) {
            mInterceptor.add(pInterceptor);
        }
    }
}


