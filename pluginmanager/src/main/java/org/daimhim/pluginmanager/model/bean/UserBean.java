package org.daimhim.pluginmanager.model.bean;

/**
 * 项目名称：org.daimhim.pluginmanager.model
 * 项目版本：muster
 * 创建时间：2018/10/17 17:43  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/17 17:43  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserBean {
    private String user_id;
    private String account_number;
    private String upTime;
    private String crateTime;
    private String user_name;
    private String pass_word;
    private String user_logo;
    private String user_phone;

    @Override
    public String toString() {
        return "UserBean{" +
                "user_id='" + user_id + '\'' +
                ", account_number='" + account_number + '\'' +
                ", upTime='" + upTime + '\'' +
                ", crateTime='" + crateTime + '\'' +
                ", user_name='" + user_name + '\'' +
                ", pass_word='" + pass_word + '\'' +
                ", user_logo='" + user_logo + '\'' +
                ", user_phone='" + user_phone + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String pUser_id) {
        user_id = pUser_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String pAccount_number) {
        account_number = pAccount_number;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String pUpTime) {
        upTime = pUpTime;
    }

    public String getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(String pCrateTime) {
        crateTime = pCrateTime;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String pUser_name) {
        user_name = pUser_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pPass_word) {
        pass_word = pPass_word;
    }

    public String getUser_logo() {
        return user_logo;
    }

    public void setUser_logo(String pUser_logo) {
        user_logo = pUser_logo;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String pUser_phone) {
        user_phone = pUser_phone;
    }
}
