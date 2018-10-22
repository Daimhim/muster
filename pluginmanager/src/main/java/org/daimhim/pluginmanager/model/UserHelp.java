package org.daimhim.pluginmanager.model;

import org.daimhim.pluginmanager.model.bean.UserBean;

/**
 * 项目名称：org.daimhim.pluginmanager.model
 * 项目版本：muster
 * 创建时间：2018/10/19 09:36  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 09:36  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserHelp {

    private final UserBean mUserBean;

    private UserHelp(){
        mUserBean = new UserBean();
    }
    public static UserHelp getInstance(){
        return SingletonHolder.sInstance;
    }
    private static class SingletonHolder {
        private static final UserHelp sInstance = new UserHelp();
    }

    public UserBean getUser() {
        return mUserBean;
    }

    public void upUserInfo(UserBean pUserBean){
        mUserBean.setUser_id(pUserBean.getUser_id());
        mUserBean.setAccount_number(pUserBean.getAccount_number());
        mUserBean.setCrateTime(pUserBean.getCrateTime());
        mUserBean.setPass_word(pUserBean.getPass_word());
        mUserBean.setUpTime(pUserBean.getUpTime());
        mUserBean.setUser_logo(pUserBean.getUser_logo());
        mUserBean.setUser_name(pUserBean.getUser_name());
        mUserBean.setUser_phone(pUserBean.getUser_phone());
    }

    public String getUserId(){
        return mUserBean.getUser_id();
    }
}
