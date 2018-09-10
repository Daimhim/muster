package org.daimhim.distance;


import org.omg.PortableInterceptor.Interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSession;
import javax.xml.ws.Response;

import io.reactivex.annotations.NonNull;

public class RetrofitManager {
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 3000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 3000;
    //写出时长，单位：毫秒
    public static final int WRITE_TIME_OUT = 3000;



    private static RetrofitManager mRetrofitManage;
    private Retrofit mRetrofit;

    private RetrofitManager() {
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
            File cacheFile = new File(DataConfig.cacheFile, "cache");
            //100Mb
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
            //增加头部信息
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request build = chain.request().newBuilder()
                            //.addHeader("Content-Type", "application/json")//设置允许请求json数据
                            .build();
                    return chain.proceed(build);
                }
            };
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Timber.i(message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //创建一个OkHttpClient并设置超时时间
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    //读取超时
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    //连接超时
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    //写出超时
                    .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .addInterceptor(mRewriteCacheControlInterceptor)
                    .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(logInterceptor)
                    .cache(cache)
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(DataConfig.BASE_DOMAIN)
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
            if (!DataConfig.getmNetConnectedListener().isNetConnected()) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl) ? CacheControl
                                .FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (DataConfig.getmNetConnectedListener().isNetConnected()) {
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
}


