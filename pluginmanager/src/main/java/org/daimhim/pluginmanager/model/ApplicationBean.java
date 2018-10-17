package org.daimhim.pluginmanager.model;

/**
 * 项目名称：org.daimhim.pluginmanager.model
 * 项目版本：muster
 * 创建时间：2018/10/17 17:44  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/17 17:44  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ApplicationBean {
    private String upTime;
    private String crateTime;
    private String user_id;
    private String app_id;
    private String app_name;
    private String app_url;
    private String app_logo;
    private String package_name;

    @Override
    public String toString() {
        return "ApplicationBean{" +
                "upTime='" + upTime + '\'' +
                ", crateTime='" + crateTime + '\'' +
                ", user_id='" + user_id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", app_name='" + app_name + '\'' +
                ", app_url='" + app_url + '\'' +
                ", app_logo='" + app_logo + '\'' +
                ", package_name='" + package_name + '\'' +
                '}';
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String pUser_id) {
        user_id = pUser_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String pApp_id) {
        app_id = pApp_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String pApp_name) {
        app_name = pApp_name;
    }

    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String pApp_url) {
        app_url = pApp_url;
    }

    public String getApp_logo() {
        return app_logo;
    }

    public void setApp_logo(String pApp_logo) {
        app_logo = pApp_logo;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String pPackage_name) {
        package_name = pPackage_name;
    }
}
