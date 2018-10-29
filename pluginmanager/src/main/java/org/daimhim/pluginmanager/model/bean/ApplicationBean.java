package org.daimhim.pluginmanager.model.bean;

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

    private String app_id;
    private String app_name;
    private String app_url;
    private String app_logo;
    private String package_name;
    private String version_name;
    private String version_code;
    private String min_sdk_version ;
    private String target_sdk_version ;

    @Override
    public String toString() {
        return "ApplicationBean{" +
                "app_id='" + app_id + '\'' +
                ", app_name='" + app_name + '\'' +
                ", app_url='" + app_url + '\'' +
                ", app_logo='" + app_logo + '\'' +
                ", package_name='" + package_name + '\'' +
                ", version_name='" + version_name + '\'' +
                ", version_code='" + version_code + '\'' +
                ", min_sdk_version='" + min_sdk_version + '\'' +
                ", target_sdk_version='" + target_sdk_version + '\'' +
                '}';
    }

    public String getMin_sdk_version() {
        return min_sdk_version;
    }

    public void setMin_sdk_version(String pMin_sdk_version) {
        min_sdk_version = pMin_sdk_version;
    }

    public String getTarget_sdk_version() {
        return target_sdk_version;
    }

    public void setTarget_sdk_version(String pTarget_sdk_version) {
        target_sdk_version = pTarget_sdk_version;
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

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String pVersion_name) {
        version_name = pVersion_name;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String pVersion_code) {
        version_code = pVersion_code;
    }

}
