package org.daimhim.pluginmanager.ui.user;


import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.request.User;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.user
 * 项目版本：muster
 * 创建时间：2018/10/19 10:12  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 10:12  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserPresenter {
    private User mUser;

    public UserPresenter() {
        mUser = RetrofitManager.getInstance().getRetrofit().create(User.class);
    }

    public void userLogin(String userName, String userPassWord){
        mUser.userLogin(userName,userPassWord);
    }

    public void userRegister(String userName,String userPassWord){

    }
}
