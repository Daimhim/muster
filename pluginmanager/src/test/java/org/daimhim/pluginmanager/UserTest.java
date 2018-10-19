package org.daimhim.pluginmanager;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.request.User;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/19 11:05  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 11:05  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserTest extends AbsInterfaceTest {
    User mUser;
    private String userName = "15015112011";
    private String passWord = "112011";
    @Before
    @Override
    public void init() {
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN(BuildConfig.BASE_URL);
        lConfig.setCacheFile(System.getProperty("user.dir"));
        lConfig.setNetConnectedListener(new NetConnectedListener() {
            @Override
            public boolean isNetConnected() {
                return true;
            }
        });
        System.out.println(lConfig.toString());
        RetrofitManager.getInstance().init(lConfig);
        mUser = RetrofitManager.getInstance().getRetrofit().create(User.class);
    }

    @Test
    public void userRegistered() {
        mUser.userRegistered(userName, passWord)
                .subscribe(new Observer<JavaResponse>() {
                    @Override
                    public void onSubscribe(Disposable pDisposable) {

                    }

                    @Override
                    public void onNext(JavaResponse pJavaResponse) {
                        System.out.println(pJavaResponse.toString());
                    }

                    @Override
                    public void onError(Throwable pThrowable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @After
    public void userLogin(){
        mUser.userLogin(userName, passWord)
                .subscribe(new Observer<JavaResponse>() {
                    @Override
                    public void onSubscribe(Disposable pDisposable) {

                    }

                    @Override
                    public void onNext(JavaResponse pJavaResponse) {
                        System.out.println(pJavaResponse.toString());
                    }

                    @Override
                    public void onError(Throwable pThrowable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
