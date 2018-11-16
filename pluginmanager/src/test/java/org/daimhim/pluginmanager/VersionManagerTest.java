package org.daimhim.pluginmanager;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.UserBean;
import org.daimhim.pluginmanager.model.request.User;
import org.daimhim.pluginmanager.model.request.VersionManager;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/11/16 11:06  星期五
 * 创建人：Administrator
 * 修改时间：2018/11/16 11:06  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class VersionManagerTest extends AbsInterfaceTest<Void> {
    private String filePath = "E:\\android_private\\muster\\pluginmanager\\cache\\2fdda3cc7cd98d10e666b0482b3fb80e7bec901d.jpg";
    private VersionManager mVersionManager;
    UserTest mUserTest;

    @Override
    public void start() {
        mVersionManager = RetrofitManager.getInstance().getRetrofit().create(VersionManager.class);
        mUserTest = new UserTest();

    }

    @Override
    public Observable<JavaResponse<Void>> onExecute() {
        mUserTest.start();
        return mUserTest.onExecute()
                .flatMap((Function<JavaResponse<UserBean>, ObservableSource<JavaResponse<Void>>>) pUserBeanJavaResponse -> {
                    Map<String, RequestBody> args = new HashMap<>();
                    args.put("versionName", RequestBody.create(MediaType.parse("multipart/form-data"), "1.0"));
                    args.put("apkPath", RequestBody.create(MediaType.parse("image/jpeg"), new File(filePath)));
                    args.put("packageName", RequestBody.create(MediaType.parse("multipart/form-data"), "org.daimhim.plugin1"));
                    args.put("apkDescription", RequestBody.create(MediaType.parse("multipart/form-data"), "123456"));
                    args.put("versionCode", RequestBody.create(MediaType.parse("multipart/form-data"), "1"));
                    args.put("targetSdkVersion", RequestBody.create(MediaType.parse("multipart/form-data"), "23"));
                    args.put("pluginId", RequestBody.create(MediaType.parse("multipart/form-data"), "5a7a1a24e71811e8bb473497f6950ce8"));
                    args.put("appLogo", RequestBody.create(MediaType.parse("multipart/form-data"), ""));
                    args.put("appName", RequestBody.create(MediaType.parse("multipart/form-data"), "plugin1"));
                    args.put("appUrl", RequestBody.create(MediaType.parse("multipart/form-data"), ""));
                    args.put("userId", RequestBody.create(MediaType.parse("multipart/form-data"), UserHelp.getInstance().getUserId()));
                    return mVersionManager.registerVersion(args);
                })
                .doOnNext(pVoidJavaResponse -> System.out.println(pVoidJavaResponse.toString()));
    }

    @Test
    @Override
    public void end() {
        VersionManagerTest lVersionManagerTest = new VersionManagerTest();
        lVersionManagerTest.start();
        lVersionManagerTest.onExecute()
                .subscribe(new ObserverCallBack<JavaResponse<Void>>() {
                    @Override
                    public void onSuccess(JavaResponse<Void> pVoidJavaResponse) {

                    }
                });
    }

    public static void main(String[] args) {
        VersionManagerTest lVersionManagerTest = new VersionManagerTest();
        lVersionManagerTest.start();
        lVersionManagerTest.onExecute()
                .subscribe(new ObserverCallBack<JavaResponse<Void>>() {
                    @Override
                    public void onSuccess(JavaResponse<Void> pVoidJavaResponse) {

                    }
                });
    }
}
