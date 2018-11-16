package org.daimhim.pluginmanager;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.UserBean;
import org.daimhim.pluginmanager.model.request.User;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.junit.Before;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
public class UserTest extends AbsInterfaceTest<UserBean> {
    User mUser;
    private String userName = "15015112011";
    private String passWord = "112011";

    @Before
    @Override
    public void start() {
        mUser = RetrofitManager.getInstance().getRetrofit().create(User.class);
    }

    @Override
    public Observable<JavaResponse<UserBean>> onExecute() {
        return mUser.userRegistered(userName, passWord)
                .flatMap((Function<JavaResponse, ObservableSource<JavaResponse<UserBean>>>) pJavaResponse -> mUser.userLogin(userName, passWord)
                        .doOnNext(pUserBeanJavaResponse -> {
                            System.out.println(pUserBeanJavaResponse.getResult().toString());
                            UserHelp.getInstance().upUserInfo(pUserBeanJavaResponse.getResult());
                        }));

    }

    @Override
    public void end() {
        start();
        onExecute()
                .subscribe(new ObserverCallBack<JavaResponse<UserBean>>() {
                    @Override
                    public void onSuccess(JavaResponse<UserBean> pUserBeanJavaResponse) {

                    }
                });
    }
}
