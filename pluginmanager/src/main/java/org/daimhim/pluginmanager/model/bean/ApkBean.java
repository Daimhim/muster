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
public class ApkBean {
    private String plugin_id;
    private String apk_id;
    private String upTime;
    private String crateTime;
    private String apk_name;
    private String apk_description;
    private String apk_url;
    private String apk_path;
    private String package_name;
    private String version_code;
    private String version_name;
    private String min_sdk_version;
    private String target_sdk_version;

    @Override
    public String toString() {
        return "ApkBean{" +
                "plugin_id='" + plugin_id + '\'' +
                ", apk_id='" + apk_id + '\'' +
                ", upTime='" + upTime + '\'' +
                ", crateTime='" + crateTime + '\'' +
                ", apk_name='" + apk_name + '\'' +
                ", apk_description='" + apk_description + '\'' +
                ", apk_url='" + apk_url + '\'' +
                ", apk_path='" + apk_path + '\'' +
                ", package_name='" + package_name + '\'' +
                ", version_code='" + version_code + '\'' +
                ", version_name='" + version_name + '\'' +
                ", min_sdk_version='" + min_sdk_version + '\'' +
                ", target_sdk_version='" + target_sdk_version + '\'' +
                '}';
    }

    public String getPlugin_id() {
        return plugin_id;
    }

    public void setPlugin_id(String pPlugin_id) {
        plugin_id = pPlugin_id;
    }

    public String getApk_id() {
        return apk_id;
    }

    public void setApk_id(String pApk_id) {
        apk_id = pApk_id;
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

    public String getApk_name() {
        return apk_name;
    }

    public void setApk_name(String pApk_name) {
        apk_name = pApk_name;
    }

    public String getApk_description() {
        return apk_description;
    }

    public void setApk_description(String pApk_description) {
        apk_description = pApk_description;
    }

    public String getApk_url() {
        return apk_url;
    }

    public void setApk_url(String pApk_url) {
        apk_url = pApk_url;
    }

    public String getApk_path() {
        return apk_path;
    }

    public void setApk_path(String pApk_path) {
        apk_path = pApk_path;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String pPackage_name) {
        package_name = pPackage_name;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String pVersion_code) {
        version_code = pVersion_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String pVersion_name) {
        version_name = pVersion_name;
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
}
