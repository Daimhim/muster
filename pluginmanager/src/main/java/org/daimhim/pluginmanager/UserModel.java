package org.daimhim.pluginmanager;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.daimhim.pluginmanager.model.UserBean;
import org.daimhim.pluginmanager.model.request.User;


/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/18 11:34  星期四
 * 创建人：Administrator
 * 修改时间：2018/10/18 11:34  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserModel extends ViewModel {
    private MutableLiveData<UserBean> mUserBeanMutableLiveData;

    public MutableLiveData<UserBean> getUserBeanMutableLiveData() {
        return mUserBeanMutableLiveData;
    }

    private void loadUser(){

    }
}
